package wincity.litao.com.util;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by yanwentao on 2016/11/3 0003.
 */

public class ItemLongClickListener implements AdapterView.OnItemLongClickListener {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Object obj = parent.getAdapter().getItem(position);
        if (obj==null)return false;
//        showDialog(view.getContext(), obj);
        return true;
    }

    /*private void showDialog(final Context context, final Object object) {
        ActionSheetDialog actionSheetDialog = new ActionSheetDialog(context)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true);
        if (object instanceof GroupCallLogs){
            final GroupCallLogs groupCallLogs = (GroupCallLogs) object;
            if (!groupCallLogs.isHermes()){
                actionSheetDialog.addSheetItem(context.getString(R.string.invite_to_toku), ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                InviteContact.InvitePhone invitePhone = new InviteContact.InvitePhone();
                                invitePhone.setContactName(groupCallLogs.getName());
                                invitePhone.setType("Mobile");
                                invitePhone.setNumber(groupCallLogs.getNumber());
//                                String number = groupCallLogs.getNumber();
//                                InviteUtil.inviteFriendsBySMS((Activity) context,number);
                                InviteActivity.lauch(context,invitePhone);
                            }
                        });
            }
        }
        actionSheetDialog.addSheetItem(context.getString(R.string.view_calllogs), ActionSheetDialog.SheetItemColor.Blue,
                new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        Intent intent = new Intent(context, CallLogDetailActivity.class);
                        intent.putExtra(GroupCallLogs.GROUP_CALL_LOGS, (GroupCallLogs) object);
                        context.startActivity(intent);
                    }
                })
                .show();
    }*/
}
