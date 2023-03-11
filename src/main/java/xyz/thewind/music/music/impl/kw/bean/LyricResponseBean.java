package xyz.thewind.music.music.impl.kw.bean;

import java.util.List;

public class LyricResponseBean {
    public Data data = new Data();

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        List<Pair> lrclist;

        public List<Pair> getLrclist() {
            return lrclist;
        }

        public void setLrclist(List<Pair> lrclist) {
            this.lrclist = lrclist;
        }

        public class Pair {
            String lineLyric;
            String time;

            public String getLineLyric() {
                return lineLyric;
            }

            public void setLineLyric(String lineLyric) {
                this.lineLyric = lineLyric;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
