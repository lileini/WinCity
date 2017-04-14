package wincity.litao.com.util;

/**
 * Created by yanwentao on 2016/11/21 0021.
 */

public class InviteUtil {

    private final static String TAG = "InviteUtil";

    /**
     * invite friends by SMS
     * @param number phone number
     */
    /*public static void inviteFriendsBySMS(final Activity activity, String... number){
        String numbers = null;
        if (number != null){
            StringBuilder builder = new StringBuilder();
            for(String temp:number){
                builder.append(temp+";");
            }
            numbers = builder.toString();
        }
        final String smsNumbers = numbers;
        Log.i(TAG,"smsNumbers: "+smsNumbers);
        if (TextUtils.isEmpty(SPUtil.getInviteMessage())){
            final ProgressDialog dialog = new ProgressDialog(activity);
            dialog.setMessage(activity.getResources().getString(R.string.incall_please_wait));
            dialog.show();



            ApiManager.getInstance().getInviteMessage()
                    .observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<InviteMessage>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + smsNumbers));
                            intent.putExtra("sms_body", activity.getResources().getString(R.string.default_invite_message)+" "+ activity.getString(R.string.default_invite_message_2)+SPUtil.getProfile().getInvite_code().toUpperCase());
                            activity.startActivity(intent);
                            dialog.dismiss();
                        }

                        @Override
                        public void onNext(InviteMessage msg) {
                            String content = msg.getContent();

                            String invitestr = content + " " + SPUtil.getProfile().getInvite_code().toUpperCase();

                            SPUtil.saveInivteMessage(invitestr);
                            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + smsNumbers));
                            intent.putExtra("sms_body",invitestr);
                            activity.startActivity(intent);
                            dialog.dismiss();
                        }
                    });
        }else {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + smsNumbers));
            intent.putExtra("sms_body", SPUtil.getInviteMessage());
            activity.startActivity(intent);
        }
    }
    *//**
     * invite friends by social apps
     * @param number phone number
     *//*
    public static void inviteFriendsBySocialApps(final Activity activity, String... number){
        String numbers = null;
        if (number != null){
            StringBuilder builder = new StringBuilder();
            for(String temp:number){
                builder.append(temp+";");
            }
            numbers = builder.toString();
        }
        final String smsNumbers = numbers;
        Log.i(TAG,"smsNumbers: "+smsNumbers);
        if (TextUtils.isEmpty(SPUtil.getInviteMessage())){
            final ProgressDialog dialog = new ProgressDialog(activity);
            dialog.setMessage(activity.getResources().getString(R.string.incall_please_wait));
            dialog.show();



            ApiManager.getInstance().getInviteMessage()
                    .observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<InviteMessage>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Intent intent  = new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_TEXT,SPUtil.getInviteMessage());
                            intent.putExtra(Intent.EXTRA_SUBJECT,activity.getString(R.string.share_subject));
                            intent.setType("text/plain");
                            activity.startActivity(Intent.createChooser(intent,activity.getString(R.string.share_via)));
//                            activity.startActivity(intent);
                            dialog.dismiss();
                        }

                        @Override
                        public void onNext(InviteMessage msg) {
                            String content = msg.getContent();
                            if (SPUtil.getProfile() == null){
                                dialog.dismiss();
                                return;
                            }
                            String invitestr = content + " " + SPUtil.getProfile().getInvite_code().toUpperCase();

                            SPUtil.saveInivteMessage(invitestr);
                            Intent intent  = new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_TEXT,invitestr);
                            intent.putExtra(Intent.EXTRA_SUBJECT,activity.getString(R.string.share_subject));
                            intent.setType("text/plain");
                            activity.startActivity(Intent.createChooser(intent,activity.getString(R.string.share_via)));
//                            activity.startActivity(intent);
                            dialog.dismiss();
                        }
                    });
        }else {
            Intent intent  = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,SPUtil.getInviteMessage());
            intent.putExtra(Intent.EXTRA_SUBJECT,activity.getString(R.string.share_subject));
            intent.setType("text/plain");
            activity.startActivity(Intent.createChooser(intent,activity.getString(R.string.share_via)));
//            activity.startActivity(intent);
        }
    }

    public static InviteContact createInviteContact(Contact contact){
        InviteContact inviteContact = new InviteContact();
        List<InviteContact.InvitePhone> invitePhoneList = new ArrayList<>(10);
        for (Phone phone:contact.getPhones()){
            if (!phone.isHermes()){
                InviteContact.InvitePhone invitePhone = new InviteContact.InvitePhone();
                invitePhone.setNumber(phone.getNumber());
                invitePhone.setType(phone.getType());
                invitePhoneList.add(invitePhone);
                inviteContact.setContactname(phone.getContactName());
            }
        }
        if (invitePhoneList.size() == 0)
            return null;
        inviteContact.setInvitePhoneList(invitePhoneList);
        return inviteContact;
    }

    public static InviteContact.InvitePhone createInvitePhone(Phone phone){
        InviteContact.InvitePhone invitePhone = new InviteContact.InvitePhone();
        if (phone.isHermes())
            return null;
        invitePhone.setContactName(phone.getContactName());
        invitePhone.setNumber(phone.getNumber());
        invitePhone.setType(phone.getType());
        return invitePhone;
    }*/

}
