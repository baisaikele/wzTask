package asd.asd.asdasd;

import java.util.List;

public class IpBean {

    /**
     * code : 0
     * msg : [{"port":"47708","ip":"60.169.124.128"},{"port":"25649","ip":"121.235.228.235"}]
     */

    private String code;
    private List<MsgBean> msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * port : 47708
         * ip : 60.169.124.128
         */

        private String port;
        private String ip;

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }
}
