package com.ruenzuo.messageslistview.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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
    private int senderColor;
    private int recipientColor;
    private int messageTextColor;
    private int dateTextColor;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE h:mm a");

    public MessageAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void setMessageTextColor(int messageTextColor) {
        this.messageTextColor = messageTextColor;
    }

    public void setDateTextColor(int dateTextColor) {
        this.dateTextColor = dateTextColor;
    }

    public void setSenderColor(int senderColor) {
        this.senderColor = senderColor;
    }

    public void setRecipientColor(int recipientColor) {
        this.recipientColor = recipientColor;
    }

    public void setSenderDrawable(Drawable drawable) {
        Bitmap bitmap;
        Canvas canvas;
        if (drawable == null) {
            Drawable defaultDrawable = getContext().getResources().getDrawable(R.drawable.mlv__default_avatar);
            bitmap = Bitmap.createBitmap(defaultDrawable.getIntrinsicWidth(), defaultDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(bitmap);
            defaultDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            defaultDrawable.draw(canvas);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        Paint paint = new Paint();
        paint.setColor(senderColor);
        paint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        Point a = new Point(0, 0);
        Point b = new Point(0, 200);
        Point c = new Point(200, 0);
        path.setFillType(Path.FillType.EVEN_ODD);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(a.x, a.y);
        path.close();
        canvas.drawPath(path, paint);
        this.senderDrawable = new BitmapDrawable(getContext().getResources(), bitmap);
    }

    public void setRecipientDrawable(Drawable drawable) {
        Bitmap bitmap;
        Canvas canvas;
        if (drawable == null) {
            Drawable defaultDrawable = getContext().getResources().getDrawable(R.drawable.mlv__default_avatar);
            bitmap = Bitmap.createBitmap(defaultDrawable.getIntrinsicWidth(), defaultDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(bitmap);
            defaultDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            defaultDrawable.draw(canvas);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        Paint paint = new Paint();
        paint.setColor(recipientColor);
        paint.setStyle(Paint.Style.FILL);
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
        canvas.drawPath(path, paint);
        this.recipientDrawable = new BitmapDrawable(getContext().getResources(), bitmap);
    }

    static class ViewHolder {
        TextView message_text;
        TextView message_date;
        ImageView avatar;
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
                    convertView = inflater.inflate(R.layout.mlv__received_message_row_layout, null);
                    break;
                }
                case MESSAGE_TYPE_SENT: {
                    convertView = inflater.inflate(R.layout.mlv__sent_message_row_layout, null);
                    break;
                }
            }
            viewHolder = new ViewHolder();
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.sender_avatar);
            viewHolder.message_text = (TextView) convertView.findViewById(R.id.message_text);
            viewHolder.message_date = (TextView) convertView.findViewById(R.id.message_date);
            viewHolder.message_container = (LinearLayout) convertView.findViewById(R.id.message_container);
            viewHolder.message_text.setTextColor(messageTextColor);
            viewHolder.message_date.setTextColor(dateTextColor);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.message_text.setText(message.getText());
        viewHolder.message_date.setText(simpleDateFormat.format(message.getDate()));
        if (message.getType() == MessageType.MESSAGE_TYPE_SENT) {
            viewHolder.avatar.setImageDrawable(senderDrawable);
            viewHolder.message_container.setBackgroundColor(senderColor);
        } else {
            viewHolder.avatar.setImageDrawable(recipientDrawable);
            viewHolder.message_container.setBackgroundColor(recipientColor);
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
