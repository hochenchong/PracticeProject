package creational.builder;

public class BuilderActor {
    private final String title;
    private final String message;

    // 私有构造函数，只有Builder才能访问
    private BuilderActor(Builder builder) {
        this.title = builder.title;
        this.message = builder.message;
    }

    // 显示对话框的方法（示例实现）
    public void show() {
        System.out.println("Title: " + title);
        System.out.println("Message: " + message);
    }

    // 建造者类
    public static class Builder {
        private String title;
        private String message;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public BuilderActor build() {
            return new BuilderActor(this);
        }
    }

    public static void main(String[] args) {
        // 使用 Builder 来构造 Dialog
        BuilderActor build = new Builder().setTitle("标题").setMessage("内容").build();
        build.show();
    }
}