package com.night.model.entity;

import java.io.Serializable;
import java.util.List;

public class WordEntity implements Serializable {

    /**
     * word_name : advise is_CRI : 1 exchange :
     * {"word_pl":"","word_third":["advises"],"word_past":["advised"],"word_done":["advised"],"word_ing":["advising"],"word_er":"","word_est":""}
     * symbols :
     * [{"ph_en":"ədˈvaɪz","ph_am":"ædˈvaɪz","ph_other":"","ph_en_mp3":"http://res.iciba.com/resource/amp3/oxford/0/39/c3/39c393230015ea19c41ca1162cf663cf.mp3","ph_am_mp3":"http://res.iciba.com/resource/amp3/1/0/a9/aa/a9aa299fb148193285e62321fb253ce4.mp3","ph_tts_mp3":"http://res-tts.iciba.com/a/9/a/a9aa299fb148193285e62321fb253ce4.mp3","parts":[{"part":"vt.","means":["（商业）通知，报告","提议，建议"]},{"part":"vi.","means":["接受劝告，商量","建议，提供意见"]}]}]
     */

    private String            word_name;

    private List<SymbolsBean> symbols;

    public String getWord_name() {
        return word_name;
    }

    public void setWord_name(String word_name) {
        this.word_name = word_name;
    }

    public List<SymbolsBean> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<SymbolsBean> symbols) {
        this.symbols = symbols;
    }

    public class SymbolsBean {
        /**
         * ph_en : ədˈvaɪz ph_am : ædˈvaɪz ph_other : ph_en_mp3 :
         * http://res.iciba.com/resource/amp3/oxford/0/39/c3/39c393230015ea19c41ca1162cf663cf.mp3
         * ph_am_mp3 :
         * http://res.iciba.com/resource/amp3/1/0/a9/aa/a9aa299fb148193285e62321fb253ce4.mp3
         * ph_tts_mp3 :
         * http://res-tts.iciba.com/a/9/a/a9aa299fb148193285e62321fb253ce4.mp3 parts :
         * [{"part":"vt.","means":["（商业）通知，报告","提议，建议"]},{"part":"vi.","means":["接受劝告，商量","建议，提供意见"]}]
         */

        private String          ph_en;

        private String          ph_am;

        private String          ph_en_mp3;

        private String          ph_am_mp3;

        private List<PartsBean> parts;

        public String getPh_en() {
            return ph_en;
        }

        public void setPh_en(String ph_en) {
            this.ph_en = ph_en;
        }

        public String getPh_am() {
            return ph_am;
        }

        public void setPh_am(String ph_am) {
            this.ph_am = ph_am;
        }

        public String getPh_en_mp3() {
            return ph_en_mp3;
        }

        public void setPh_en_mp3(String ph_en_mp3) {
            this.ph_en_mp3 = ph_en_mp3;
        }

        public String getPh_am_mp3() {
            return ph_am_mp3;
        }

        public void setPh_am_mp3(String ph_am_mp3) {
            this.ph_am_mp3 = ph_am_mp3;
        }

        public List<PartsBean> getParts() {
            return parts;
        }

        public void setParts(List<PartsBean> parts) {
            this.parts = parts;
        }

        public class PartsBean {
            /**
             * part : vt. means : ["（商业）通知，报告","提议，建议"]
             */

            private String       part;

            private List<String> means;

            public String getPart() {
                return part;
            }

            public void setPart(String part) {
                this.part = part;
            }

            public List<String> getMeans() {
                return means;
            }

            public void setMeans(List<String> means) {
                this.means = means;
            }
        }
    }
}
