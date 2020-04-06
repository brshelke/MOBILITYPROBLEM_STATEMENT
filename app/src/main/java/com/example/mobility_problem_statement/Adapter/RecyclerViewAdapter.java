package com.example.mobility_problem_statement.Adapter;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobility_problem_statement.Model.information;
import com.example.mobility_problem_statement.R;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    ArrayList<information> idandauthorlist= new ArrayList<>();


    public RecyclerViewAdapter(ArrayList<information> idandauthorlist) {

        this.idandauthorlist = idandauthorlist;
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.name.setText(idandauthorlist.get(position).getAuthor());
        String id = idandauthorlist.get(position).getId();
        String imgURL  = "https://picsum.photos/300/300?image="+id;
        new DownLoadImageTask(holder.image).execute(imgURL);
    }

    @Override
    public int getItemCount() {
        return idandauthorlist.size();
    }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public int getItemViewType(int position) { return position; }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;


        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.aname) ;
            image = (ImageView) itemView.findViewById(R.id.aimage);


        }
    }



    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap dowloadimage = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                dowloadimage = BitmapFactory.decodeStream(is);
            }catch(Exception e){
                e.printStackTrace();
            }
            return dowloadimage;
        }


        protected void onPostExecute(Bitmap result){

            imageView.setImageBitmap(result);
        }
    }
}
