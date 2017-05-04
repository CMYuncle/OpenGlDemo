package com.example.cmy.opengldemo;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
//https://blog.piasy.com/2016/06/07/Open-gl-es-android-2-part-1/
//http://blog.csdn.net/zhoudailiang/article/details/50176143
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GLSurfaceView glSurfaceView = (android.opengl.GLSurfaceView) findViewById(R.id.mGLSurfaceView);
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(new MyRenderer());
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    static class MyRenderer implements GLSurfaceView.Renderer {
        private static final String VERTEX_SHADER = "attribute vec4 vPosition;\n"
                + "void main(){\n"
                + " gl_Position = vPosition;\n"
                + "}";

        private static final String FRAGMENT_SHADER = "precision mediump float;\n"
                + "void main(){\n"
                + " gl_FragColor = vec4(0.5,0,0,1);\n"
                + "}";
        private static final float[] VERTEX = {
                0, 1, 0.0f,
                -0.5f, -1, 0.0f,
                1f, -1, 0.0f,
        };

        private final FloatBuffer mVertextBuffer;
        private int mProgram;
        private  int mPositionHandle;

        MyRenderer(){
            mVertextBuffer = ByteBuffer.allocateDirect(VERTEX.length*4)
                    .order(ByteOrder.nativeOrder())
                    .asFloatBuffer()
                    .put(VERTEX);
            mVertextBuffer.position(0);
        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            mProgram = GLES20.glCreateProgram();
            int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, VERTEX_SHADER);
            int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,FRAGMENT_SHADER);
            GLES20.glAttachShader(mProgram,vertexShader);
            GLES20.glAttachShader(mProgram,fragmentShader);

            GLES20.glLinkProgram(mProgram);
            mPositionHandle = GLES20.glGetAttribLocation(mProgram,"vPosition");

        }

        @Override
        public void onDrawFrame(GL10 gl) {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
            GLES20.glUseProgram(mProgram);
            GLES20.glEnableVertexAttribArray(mPositionHandle);
            GLES20.glVertexAttribPointer(mPositionHandle,3,GLES20.GL_FLOAT,false,12,mVertextBuffer);

            GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,3);
            GLES20.glDisableVertexAttribArray(mPositionHandle);
        }

        static int loadShader(int type, String shaderCode) {
            int shader = GLES20.glCreateShader(type);
            GLES20.glShaderSource(shader, shaderCode);
            GLES20.glCompileShader(shader);
            return shader;
        }
    }
}
