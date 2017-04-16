package wincity.litao.com.bus;

public interface BaseEvent {
    void setMessage(Object o);
    Object getMessage();
    enum CommonEvnet implements BaseEvent{

        SUCCESS,
        UNSUCCESS;
        private Object o;
        @Override
        public void setMessage(Object o) {
            this.o = o;
        }

        @Override
        public Object getMessage() {
            return o;
        }
    }

    enum RequestOtpEvent implements BaseEvent{
        SUCCESS,
        UNSUCCESS;
        private Object o;

        @Override
        public void setMessage(Object o) {

        }

        @Override
        public Object getMessage() {
            return null;
        }
    }


}
