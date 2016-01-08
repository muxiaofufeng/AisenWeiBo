package org.aisen.weibo.sina.ui.fragment.comment;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.aisen.android.component.bitmaploader.BitmapLoader;
import org.aisen.android.support.inject.ViewInject;
import org.aisen.android.ui.fragment.adapter.ARecycleViewItemView;
import org.aisen.weibo.sina.R;
import org.aisen.weibo.sina.sinasdk.bean.StatusComment;
import org.aisen.weibo.sina.sinasdk.bean.WeiBoUser;
import org.aisen.weibo.sina.support.utils.AisenHelper;
import org.aisen.weibo.sina.support.utils.AisenUtils;
import org.aisen.weibo.sina.support.utils.ImageConfigUtils;
import org.aisen.weibo.sina.ui.widget.AisenTextView;

/**
 * Created by wangdan on 16/1/8.
 */
public class TimelineCommentItemView extends ARecycleViewItemView<StatusComment> {

    public static final int LAYOUT_RES = R.layout.item_timeline_comment;

    @ViewInject(id = R.id.imgPhoto)
    ImageView imgPhoto;
    @ViewInject(id = R.id.txtName)
    TextView txtName;
    @ViewInject(id = R.id.txtDesc)
    TextView txtDesc;
    @ViewInject(id = R.id.txtContent)
    AisenTextView txtContent;

    private TimelineCommentFragment mFragment;

    public TimelineCommentItemView(TimelineCommentFragment fragment, View itemView) {
        super(itemView);

        this.mFragment = fragment;
    }

    @Override
    public void onBindData(View convertView, StatusComment data, int position) {
        WeiBoUser user = data.getUser();
        if (user != null) {
            BitmapLoader.getInstance().display(mFragment,
                                                    AisenUtils.getUserPhoto(user),
                                                    imgPhoto, ImageConfigUtils.getLargePhotoConfig());
            AisenHelper.userShow(imgPhoto, user);
            txtName.setText(AisenUtils.getUserScreenName(user));
        }
        else {
            AisenHelper.userShow(imgPhoto, null);
            txtName.setText(R.string.error_cmts);
            imgPhoto.setImageResource(R.drawable.user_placeholder);
        }

        txtContent.setContent(data.getText());
        AisenUtils.setTextSize(txtContent);

        String createAt = AisenUtils.convDate(data.getCreated_at());
        String from = String.format("%s", Html.fromHtml(data.getSource()));
        String desc = String.format("%s %s", createAt, from);
        txtDesc.setText(desc);
    }

}