java实现树形输出效果：

```
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class mappingtool {

    public static void main(String[] args) {
        layer m = new layer("project");
        m.addNext(new layer("num 1"));
        m.addNext(new layer("num 2"));
        m.addNext(new layer("num 3"));
        layer layer1 = m.getLayerByNum(1);
        layer1.addNext(new layer("1.1"));
        layer1.addNext(new layer("1.2"));
        layer1.addNext(new layer("1.3"));

        layer layer1_1 = layer1.getLayerByNum(1);
        layer1_1.addNext(new layer("1.1.1"));
        layer1_1.addNext(new layer("1.1.2"));
        layer1_1.addNext(new layer("1.1.3"));

        layer layer2 = m.getLayerByNum(2);
        layer2.addNext(new layer("1.1"));
        layer2.addNext(new layer("1.2"));
        layer2.addNext(new layer("1.3"));
        layer layer3 = m.getLayerByNum(3);
        layer3.addNext(new layer("1.1"));
        layer3.addNext(new layer("1.2"));
        layer3.addNext(new layer("1.3"));





        System.out.println("" + m.toString());
    }


    static class layer {
        private String content = "default content";
        private String content_length = "                                                                          ";
        private int blank_num = 0;
        private List<layer> next = new ArrayList<>();

        public layer(String content) {
            this.content = content;
            this.blank_num = this.content.length();
            setBlank();
        }

        private void setBlank() {
            if (this.blank_num < this.content_length.length()) {
                this.content_length = this.content_length.substring(0,this.blank_num);
            } else {
                char[] chars = new char[this.blank_num];  Arrays.fill(chars,' ');
                this.content_length = new String(chars);
            }

        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void addNext(layer layer){
            layer.blank_num = layer.content.length() + this.content_length.length();
            layer.setBlank();
            next.add(layer);
        }

        public layer getLayerByNum(int num){
            return this.next.get(num-1);
        }

        public void removeLayer(layer layer){
            next.remove(layer);
        }

        @Override
        public String toString() {
            String str = ""+this.content+"\n";
            if (this.next==null || this.next.size() < 1){
                return str;
            }
            for(int i=0;i<this.next.size();i++){
                str = str + this.content_length + "|___"+this.next.get(i).toString();
            }
            return str;
        }
    }
}


```
