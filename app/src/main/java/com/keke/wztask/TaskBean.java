package com.keke.wztask;

import java.util.List;

public class TaskBean {

    /**
     * code : 0
     * msg : Success
     * result : {"taskid":1,"requestip":[{"ip":"123.123.123.121","port":"1234"},{"ip":"123.123.123.122","port":"4567"},{"ip":"123.123.123.123","port":"8912"}],"taskitem":[{"id":1,"forwardplatformid":2,"resources":[{"url":"http://wwww.bbb.com","useragent":"xxxsssscccc","device":"Android","system":"8","platform":"wx"},{"url":"http://wwww.bbb.com","useragent":"xxxsssscccc","device":"Android","system":"8","platform":"wx"}]},{"id":2,"forwardplatformid":3,"resources":[{"url":"http://wwww.bbb.com","useragent":"xxxsssscccc","device":"Android","system":"8","platform":"wx"},{"url":"http://wwww.bbb.com","useragent":"xxxsssscccc","device":"Android","system":"8","platform":"wx"}]}]}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * taskid : 1
         * requestip : [{"ip":"123.123.123.121","port":"1234"},{"ip":"123.123.123.122","port":"4567"},{"ip":"123.123.123.123","port":"8912"}]
         * taskitem : [{"id":1,"forwardplatformid":2,"resources":[{"url":"http://wwww.bbb.com","useragent":"xxxsssscccc","device":"Android","system":"8","platform":"wx"},{"url":"http://wwww.bbb.com","useragent":"xxxsssscccc","device":"Android","system":"8","platform":"wx"}]},{"id":2,"forwardplatformid":3,"resources":[{"url":"http://wwww.bbb.com","useragent":"xxxsssscccc","device":"Android","system":"8","platform":"wx"},{"url":"http://wwww.bbb.com","useragent":"xxxsssscccc","device":"Android","system":"8","platform":"wx"}]}]
         */

        private int taskid;
        private List<RequestipBean> requestip;
        private List<TaskitemBean> taskitem;

        public int getTaskid() {
            return taskid;
        }

        public void setTaskid(int taskid) {
            this.taskid = taskid;
        }

        public List<RequestipBean> getRequestip() {
            return requestip;
        }

        public void setRequestip(List<RequestipBean> requestip) {
            this.requestip = requestip;
        }

        public List<TaskitemBean> getTaskitem() {
            return taskitem;
        }

        public void setTaskitem(List<TaskitemBean> taskitem) {
            this.taskitem = taskitem;
        }

        public static class RequestipBean {
            /**
             * ip : 123.123.123.121
             * port : 1234
             */

            private String ip;
            private String port;

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getPort() {
                return port;
            }

            public void setPort(String port) {
                this.port = port;
            }
        }

        public static class TaskitemBean {
            /**
             * id : 1
             * forwardplatformid : 2
             * resources : [{"url":"http://wwww.bbb.com","useragent":"xxxsssscccc","device":"Android","system":"8","platform":"wx"},{"url":"http://wwww.bbb.com","useragent":"xxxsssscccc","device":"Android","system":"8","platform":"wx"}]
             */

            private int id;
            private int forwardplatformid;
            private List<ResourcesBean> resources;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getForwardplatformid() {
                return forwardplatformid;
            }

            public void setForwardplatformid(int forwardplatformid) {
                this.forwardplatformid = forwardplatformid;
            }

            public List<ResourcesBean> getResources() {
                return resources;
            }

            public void setResources(List<ResourcesBean> resources) {
                this.resources = resources;
            }

            public static class ResourcesBean {
                /**
                 * url : http://wwww.bbb.com
                 * useragent : xxxsssscccc
                 * device : Android
                 * system : 8
                 * platform : wx
                 */

                private String url;
                private String useragent;
                private String device;
                private String system;
                private String platform;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getUseragent() {
                    return useragent;
                }

                public void setUseragent(String useragent) {
                    this.useragent = useragent;
                }

                public String getDevice() {
                    return device;
                }

                public void setDevice(String device) {
                    this.device = device;
                }

                public String getSystem() {
                    return system;
                }

                public void setSystem(String system) {
                    this.system = system;
                }

                public String getPlatform() {
                    return platform;
                }

                public void setPlatform(String platform) {
                    this.platform = platform;
                }
            }
        }
    }
}
