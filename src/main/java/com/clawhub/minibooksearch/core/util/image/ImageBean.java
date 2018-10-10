package com.clawhub.minibooksearch.core.util.image;

/**
 * <Description> <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018 -09-28 <br>
 */
public class ImageBean {

    /**
     * The Width.
     */
    private float width;
    /**
     * The Height.
     */
    private float height;
    /**
     * The Path.
     */
    private String path;

    /**
     * Instantiates a new Image bean.
     *
     * @param width  the width
     * @param height the height
     * @param path   the path
     */
    public ImageBean(float width, float height, String path) {
        this.height = height;
        this.width = width;
        this.path = path;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets path.
     *
     * @param path the path
     */
    public void setPath(String path) {
        this.path = path;
    }
}
