package sneha.com.nytimes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import sneha.com.nytimes.R;
import sneha.com.nytimes.activity.DetailActivity;
import sneha.com.nytimes.helper.CircularImageView;
import sneha.com.nytimes.models.Result;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {

    Context context;
    ArrayList<Result> results;

    /**
     * Method to give reference to the widgets
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle, textViewAuthor, textViewDate;
        private ImageView imageViewMore;
        private CircularImageView circularImageView;
private FrameLayout frameDetail;
        public MyViewHolder(View view) {
            super(view);
            frameDetail=view.findViewById(R.id.frameDetail);
            textViewTitle = view.findViewById(R.id.textViewTitle);
            textViewAuthor = view.findViewById(R.id.textViewAuthor);
            textViewDate = view.findViewById(R.id.textViewDate);
            imageViewMore = view.findViewById(R.id.imageViewMore);
            circularImageView=view.findViewById(R.id.circularImageView);

        }

    }


    public NewsListAdapter(Context context, ArrayList<Result> results) {
        this.context = context;
        this.results = results;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_layout_detail, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textViewTitle.setText(results.get(position).getTitle());
        holder.textViewAuthor.setText(results.get(position).getByline());
        holder.textViewDate.setText(results.get(position).getPublishedDate());

        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder))
                .load(results.get(position).getMedia().get(0).getMediaMetadata().get(0).getUrl())
                .into(holder.circularImageView);

        holder.frameDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Result newsData=results.get(position);
                intent.putExtra("result",newsData);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

}
