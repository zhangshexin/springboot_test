package com.sam.sct.mytest.ucenter.action;


import com.sam.sct.mytest.ucenter.beans.GirlProperties;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tensorflow.*;
import org.tensorflow.types.UInt8;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {


    @Value("${girl.name}")
    private String name;

    @Autowired
    private GirlProperties girlProperties;

    @ApiOperation(value = "哈罗罗", notes = "每次你要放弃的时候生活就给你一颗糖")
    @RequestMapping(value = {"/hello", "/hi", "/"}, method = RequestMethod.GET)
    public String say() {
        return girlProperties.getAge() + " hi you !! " + name;
    }

    @ApiOperation(value = "检测图片是否为色情图", notes = "请传入图片或图片url，要是传的是别的那你自己负责,提交表单注意enctype=\"multipart/form-data\"")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pornographic", value = "要检测的图片", dataType = "MultipartFile")
    })
    @RequestMapping(value = "/pic/pornographic", method = RequestMethod.POST)
    public String detectionPicIsPornographic(@RequestParam(value = "pornographic") MultipartFile file, @RequestParam(value = "pornographicUrl") String picUrl, HttpServletRequest request, HttpServletResponse response) {
        String dir = "/Users/qbq_wzk/zhangshexin/Seafile/web/springboot_test/tempFile/";
        File dirF = new File(dir);
        if (!dirF.isDirectory())
            dirF.mkdirs();
        String fileUrl = dir + file.getOriginalFilename();

        try {
            File newfile = new File(fileUrl);
            if (!newfile.exists()) {
                newfile.createNewFile();
            }
            file.transferTo(newfile);
            String rootPath = this.getClass().getResource("/").getPath();

           return excutePython(rootPath+"/nsfw_predict.py",newfile.getAbsolutePath(),rootPath+"/dnn/1547856517");
//            byte[] graphDef = readAllBytesOrExit(Paths.get(rootPath, "/dnn/pornographic_model_frozen.pb"));
//            List<String> labels =
//                    readAllLinesOrExit(Paths.get(rootPath, "/dnn/pornographic_graph_label_strings.txt"));
//            byte[] imageBytes = readAllBytesOrExit(Paths.get(newfile.getAbsolutePath()));
//
//            Tensor<Float> image = constructAndExecuteGraphToNormalizeImage(imageBytes);
//            float[] labelProbabilities = executeInceptionGraph(graphDef, image);
//            int bestLabelIdx = maxIndex(labelProbabilities);
//            return "{label:\"" + labels.get(bestLabelIdx) + "\",score:\"" + (labelProbabilities[bestLabelIdx] * 100f) + "\"}";
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
//            FileUtils.deleteFile(fileUrl);
        }
        return "{你就是个哈皮}";
    }

    private String excutePython(String pythonFilePath,String picPath,String modelPath){
        try {
            String[] args = new String[] { "python", pythonFilePath, picPath,modelPath};
            Process proc = Runtime.getRuntime().exec(args);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line=null;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();
            proc.waitFor();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Tensor<Float> constructAndExecuteGraphToNormalizeImage(byte[] imageBytes) {
        try (Graph g = new Graph()) {
            GraphBuilder b = new GraphBuilder(g);
            // Some constants specific to the pre-trained model at:
            // https://storage.googleapis.com/download.tensorflow.org/models/inception5h.zip
            //
            // - The model was trained with images scaled to 224x224 pixels.
            // - The colors, represented as R, G, B in 1-byte each were converted to
            //   float using (value - Mean)/Scale.
            final int H = 224;
            final int W = 224;
            final float mean = 117f;
            final float scale = 1f;

            // Since the graph is being constructed once per execution here, we can use a constant for the
            // input image. If the graph were to be re-used for multiple input images, a placeholder would
            // have been more appropriate.
            final Output<String> input = b.constant("input", imageBytes);
            final Output<Float> output =
                    b.div(
                            b.sub(
                                    b.resizeBilinear(
                                            b.expandDims(
                                                    b.cast(b.decodeJpeg(input, 3), Float.class),
                                                    b.constant("make_batch", 0)),
                                            b.constant("size", new int[] {H, W})),
                                    b.constant("mean", mean)),
                            b.constant("scale", scale));
            try (Session s = new Session(g)) {
                // Generally, there may be multiple output tensors, all of them must be closed to prevent resource leaks.
                return s.runner().fetch(output.op().name()).run().get(0).expect(Float.class);
            }
        }
    }

    private static float[] executeInceptionGraph(byte[] graphDef, Tensor<Float> image) {
        try (Graph g = new Graph()) {
            g.importGraphDef(graphDef);
            try (Session s = new Session(g);
                 // Generally, there may be multiple output tensors, all of them must be closed to prevent resource leaks.
                 Tensor<Float> result =
                         s.runner().feed("input_tensor", image).fetch("softmax_tensor").run().get(0).expect(Float.class)) {
                final long[] rshape = result.shape();
                if (result.numDimensions() != 2 || rshape[0] != 1) {
                    throw new RuntimeException(
                            String.format(
                                    "Expected model to produce a [1 N] shaped tensor where N is the number of labels, instead it produced one with shape %s",
                                    Arrays.toString(rshape)));
                }
                int nlabels = (int) rshape[1];
                return result.copyTo(new float[1][nlabels])[0];
            }
        }
    }

    private static int maxIndex(float[] probabilities) {
        int best = 0;
        for (int i = 1; i < probabilities.length; ++i) {
            if (probabilities[i] > probabilities[best]) {
                best = i;
            }
        }
        return best;
    }

    private static byte[] readAllBytesOrExit(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            System.err.println("Failed to read [" + path + "]: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }

    private static List<String> readAllLinesOrExit(Path path) {
        try {
            return Files.readAllLines(path, Charset.forName("UTF-8"));
        } catch (IOException e) {
            System.err.println("Failed to read [" + path + "]: " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    // In the fullness of time, equivalents of the methods of this class should be auto-generated from
    // the OpDefs linked into libtensorflow_jni.so. That would match what is done in other languages
    // like Python, C++ and Go.
    static class GraphBuilder {
        GraphBuilder(Graph g) {
            this.g = g;
        }

        Output<Float> div(Output<Float> x, Output<Float> y) {
            return binaryOp("Div", x, y);
        }

        <T> Output<T> sub(Output<T> x, Output<T> y) {
            return binaryOp("Sub", x, y);
        }

        <T> Output<Float> resizeBilinear(Output<T> images, Output<Integer> size) {
            return binaryOp3("ResizeBilinear", images, size);
        }

        <T> Output<T> expandDims(Output<T> input, Output<Integer> dim) {
            return binaryOp3("ExpandDims", input, dim);
        }

        <T, U> Output<U> cast(Output<T> value, Class<U> type) {
            DataType dtype = DataType.fromClass(type);
            return g.opBuilder("Cast", "Cast")
                    .addInput(value)
                    .setAttr("DstT", dtype)
                    .build()
                    .<U>output(0);
        }

        Output<UInt8> decodeJpeg(Output<String> contents, long channels) {
            return g.opBuilder("DecodeJpeg", "DecodeJpeg")
                    .addInput(contents)
                    .setAttr("channels", channels)
                    .build()
                    .<UInt8>output(0);
        }

        <T> Output<T> constant(String name, Object value, Class<T> type) {
            try (Tensor<T> t = Tensor.<T>create(value, type)) {
                return g.opBuilder("Const", name)
                        .setAttr("dtype", DataType.fromClass(type))
                        .setAttr("value", t)
                        .build()
                        .<T>output(0);
            }
        }
        Output<String> constant(String name, byte[] value) {
            return this.constant(name, value, String.class);
        }

        Output<Integer> constant(String name, int value) {
            return this.constant(name, value, Integer.class);
        }

        Output<Integer> constant(String name, int[] value) {
            return this.constant(name, value, Integer.class);
        }

        Output<Float> constant(String name, float value) {
            return this.constant(name, value, Float.class);
        }

        private <T> Output<T> binaryOp(String type, Output<T> in1, Output<T> in2) {
            return g.opBuilder(type, type).addInput(in1).addInput(in2).build().<T>output(0);
        }

        private <T, U, V> Output<T> binaryOp3(String type, Output<U> in1, Output<V> in2) {
            return g.opBuilder(type, type).addInput(in1).addInput(in2).build().<T>output(0);
        }
        private Graph g;
    }
}
