package com.example.cmy.opengldemo.View;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by CMY on 2017/5/5.
 */

public class Square {
    private float vertices[] = {
      -1.0f,1.0f,0.0f,
            -1.0f,-1.0f,0.0f,
            1.0f,-1.0f,0.0f,
            1.0f,1.0f,0.0f,
    };

    private short[] indices = {0,1,2,0,2,3};
    protected FloatBuffer vertextBuffer;
    private ShortBuffer indexBuffer;

    public Square(){
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());
        vertextBuffer = vbb.asFloatBuffer();
        vertextBuffer.put(vertices);
        vertextBuffer.position(0);

        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length*4);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);

    }

    public void draw(GL10 gl){
        gl.glFrontFace(GL10.GL_CCW);

        gl.glEnable(GL10.GL_CULL_FACE);

        gl.glCullFace(GL10.GL_BACK);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glVertexPointer(3,GL10.GL_FLOAT,0,
                vertextBuffer);

        gl.glDrawElements(GL10.GL_TRIANGLES,indices.length,
                GL10.GL_UNSIGNED_SHORT,indexBuffer);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
