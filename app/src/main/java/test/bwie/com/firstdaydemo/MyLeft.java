package test.bwie.com.firstdaydemo;

/**
 * date: 2017/4/8.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class MyLeft {
    private int image;
    private String text;

    public MyLeft(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public MyLeft() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
