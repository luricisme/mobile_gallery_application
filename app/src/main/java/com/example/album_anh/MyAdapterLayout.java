package com.example.album_anh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterLayout extends RecyclerView.Adapter<MyAdapterLayout.MyViewHolder> {

    private List<Integer> imageResIds; // Danh sách ID tài nguyên ảnh
    private Context context;
    private int typeLayout; // Trạng thái hiện tại của layout

    public MyAdapterLayout(List<Integer> imageResIds, Context context, int _typeLayout) {
        this.imageResIds = imageResIds;
        this.context = context;
        this.typeLayout = _typeLayout; // Khởi tạo trạng thái
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        // Tùy thuộc vào trạng thái, chọn layout
        if (viewType == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_home_layout_1, parent, false);
        } else if (viewType == 2) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_home_layout_2, parent, false);
        }
        else if (viewType == 3) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_home_layout_3, parent, false);
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int poLayout1 = position;
        int poLayout2 = 2*position;
        int poLayout3 = 3*position;
        if (typeLayout == 1) {
            holder.imageView1.setImageResource(imageResIds.get(poLayout1)); // Sử dụng cho layout 1
        } else if (typeLayout == 2) {
            // Có thể cần điều chỉnh cách chọn ảnh cho layout 2
            if (poLayout2 < imageResIds.size())
                holder.imageView1.setImageResource(imageResIds.get(poLayout2));
            if (poLayout2 + 1 < imageResIds.size())
                holder.imageView2.setImageResource(imageResIds.get(poLayout2 + 1));
        }
        else if (typeLayout == 3) {
            // Có thể cần điều chỉnh cách chọn ảnh cho layout 2
            if (poLayout3 < imageResIds.size())
                holder.imageView1.setImageResource(imageResIds.get(poLayout3));
            if (poLayout3 + 1 < imageResIds.size())
                holder.imageView2.setImageResource(imageResIds.get(poLayout3 + 1));
            if (poLayout3 + 2 < imageResIds.size())
                holder.imageView3.setImageResource(imageResIds.get(poLayout3 + 2));
        }
    }


    @Override
    public int getItemCount() {
        if (typeLayout == 1) {
            return imageResIds.size();
        } else if (typeLayout == 2) {
            return (imageResIds.size() + 1) / 2;
        } else if (typeLayout == 3) {
            return (imageResIds.size() + 2) / 3;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        // Trả về loại layout dựa vào biến typeLayout
        return typeLayout;
    }

    public void setLayoutType(int _typeLayout) {
        this.typeLayout = _typeLayout; // Cập nhật trạng thái
        notifyDataSetChanged(); // Thay đổi adapter
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.imageView1);
            imageView2 = itemView.findViewById(R.id.imageView2);
            imageView3 = itemView.findViewById(R.id.imageView3);
            // Nếu layout không chứa imageView2 và imageView3 thì đặt chúng thành null
            if (imageView1 == null) {
                imageView1 = itemView.findViewById(R.id.imageView); // layout 1
                imageView2 = null;
                imageView3 = null;
            } else if (imageView2 == null) {
                imageView2 = itemView.findViewById(R.id.imageView2);
                imageView3 = null;
            } else if (imageView3 == null) {
                imageView3 = null; // Không sử dụng imageView3 trong layout
            }
        }
    }

}
