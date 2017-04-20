package wincity.litao.com.bus;

public interface BaseEvent {
    void setMessage(Object o);
    Object getMessage();
    void setError_code(String s);
    String getError_code();
    enum CommonEvnet implements BaseEvent{

        SUCCESS,
        UNSUCCESS;
        private Object o;
        private String error_code;
        @Override
        public void setMessage(Object o) {
            this.o = o;
        }

        @Override
        public Object getMessage() {
            return o;
        }
        @Override
        public String getError_code() {
            return error_code;
        }
        @Override
        public void setError_code(String error_code) {
            this.error_code = error_code;
        }
    }

    enum RequestOtpEvent implements BaseEvent{
        SUCCESS,
        UNSUCCESS;
        private Object o;
        private String error_code;

        @Override
        public void setMessage(Object o) {

        }

        @Override
        public Object getMessage() {
            return null;
        }

        @Override
        public String getError_code() {
            return error_code;
        }
        @Override
        public void setError_code(String error_code) {
            this.error_code = error_code;
        }
    }


}
