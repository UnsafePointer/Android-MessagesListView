package com.ruenzuo.messageslistview.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ruenzuo.messageslistview.library.R;
import com.ruenzuo.messageslistview.models.Message;
import com.ruenzuo.messageslistview.models.MessageType;

import java.text.SimpleDateFormat;

/**
 * Created by ruenzuo on 27/05/14.
 */
public class MessageAdapter extends ArrayAdapter<Message> {

    private Drawable senderDrawable;
    private Drawable recipientDrawable;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE h:mm a");

    public MessageAdapter(Context context, int resource) {
        super(context, resource);
    }

    public Drawable getSenderDrawable() {
        return senderDrawable;
    }

    public void setSenderDrawable(int drawableResource) {
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), drawableResource);
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(mutableBitmap);
        Paint red = new Paint();
        red.setColor(Color.parseColor("#DFF2D9"));
        red.setStyle(Paint.Style.FILL);
        Path path = new Path();
        Point a = new Point(0, 0);
        Point b = new Point(0, 200);
        Point c = new Point(200, 0);
        path.setFillType(Path.FillType.EVEN_ODD);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(a.x, a.y);
        path.close();
        canvas.drawPath(path, red);
        this.senderDrawable = new BitmapDrawable(getContext().getResources(), mutableBitmap);
    }

    public Drawable getRecipientDrawable() {
        return recipientDrawable;
    }

    public void setRecipientDrawable(int drawableResource) {
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), drawableResource);
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(mutableBitmap);
        Paint red = new Paint();
        red.setColor(Color.parseColor("#FFFFFF"));
        red.setStyle(Paint.Style.FILL);
        Path path = new Path();
        Point a = new Point(bitmap.getWidth(), 0);
        Point b = new Point(bitmap.getWidth(), 200);
        Point c = new Point(bitmap.getWidth() - 200, 0);
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(a.x, a.y);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(a.x, a.y);
        path.close();
        canvas.drawPath(path, red);
        this.recipientDrawable = new BitmapDrawable(getContext().getResources(), mutableBitmap);
    }

    static class ViewHolder {
        TextView message_text;
        TextView message_date;
        ImageView sender_avatar;
        LinearLayout message_container;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Message message = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            switch (message.getType()) {
                case MESSAGE_TYPE_RECEIVED: {
                    convertView = inflater.inflate(R.layout.received_message_row_layout, null);
                    break;
                }
                case MESSAGE_TYPE_SENT: {
                    convertView = inflater.inflate(R.layout.sent_message_row_layout, null);
                    break;
                }
            }
            viewHolder = new ViewHolder();
            viewHolder.sender_avatar = (ImageView) convertView.findViewById(R.id.sender_avatar);
            viewHolder.message_text = (TextView) convertView.findViewById(R.id.message_text);
            viewHolder.message_date = (TextView) convertView.findViewById(R.id.message_date);
            viewHolder.message_container = (LinearLayout) convertView.findViewById(R.id.message_container);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.message_text.setText(message.getText());
        viewHolder.message_date.setText(simpleDateFormat.format(message.getDate()));
        if (message.getType() == MessageType.MESSAGE_TYPE_SENT) {
            viewHolder.sender_avatar.setImageDrawable(senderDrawable);
            viewHolder.message_container.setBackgroundColor(Color.parseColor("#DFF2D9"));
        } else {
            viewHolder.sender_avatar.setImageDrawable(recipientDrawable);
            viewHolder.message_container.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = getItem(position);
        return message.getType().toInt();
    }

    @Override
    public int getViewTypeCount() {
        return MessageType.values().length;
    }

}
