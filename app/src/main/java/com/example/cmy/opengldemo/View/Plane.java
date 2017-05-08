package com.example.cmy.opengldemo.View;

/**
 * Created by CMY on 2017/5/8.
 */

public class Plane extends Mesh {
    public Plane() {
        this(1, 1, 1, 1);
    }

    public Plane(int width, int height) {
        this(width, height, 1, 1);
    }

    public Plane(float width, float height, int widthSegments,
                 int heightSegments) {
        float[] vertices = new float[(widthSegments + 1)
                * (heightSegments + 1) * 3];
        short[] indices = new short[(widthSegments + 1)
                * (heightSegments + 1) * 6];

        float xoffset = width / -2;

        float yoffset = height / -2;

        float xWidth = width / (widthSegments);
        float yHeight = height / (heightSegments);

        int currentVertex = 0;
        int currentIndex = 0;

        short w = (short) (widthSegments + 1);
        for (int y = 0; y < heightSegments + 1; y++) {
            for (int x = 0; x < widthSegments + 1; x++) {
                vertices[currentVertex] = xoffset + x * xWidth;
                vertices[currentVertex + 1] = yoffset + y * yHeight;
                vertices[currentVertex + 2] = 0;

                currentVertex += 3;

                int n = y * (widthSegments + 1) + x;

                if (y < heightSegments && x < widthSegments) {
                    indices[currentIndex] = (short) n;
                    indices[currentIndex + 1] = (short) (n + 1);
                    indices[currentIndex + 2] = (short) (n + w);
                    indices[currentIndex + 3] = (short) (n + 1);
                    indices[currentIndex + 4] = (short) (n + 1 + w);
                    indices[currentIndex + 5] = (short) (n + 1 + w - 1);

                    currentIndex += 6;
                }

            }
        }
        setIndices(indices);
        setVerticesBuffer(vertices);
    }
}
