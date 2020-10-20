package github.com.kazetavi.sonofy.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import github.com.kazetavi.sonofy.R;
import github.com.kazetavi.sonofy.models.Publication;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationViewHolder> {

    private List<Publication> publications;

    public PublicationAdapter(List<Publication> publications) {
        this.publications = publications;
    }

    @NonNull
    @Override
    public PublicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.publication, parent, false);

        return new PublicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicationViewHolder holder, int position) {
        Publication publication = publications.get(position);
        holder.titreTextView.setText(publication.getTitre());
        Picasso.get().load(publication.getMiniatureUrl()).into(holder.miniatureImageView);

    }

    @Override
    public int getItemCount() {
        return publications.size();
    }

    public static class PublicationViewHolder extends RecyclerView.ViewHolder {

        TextView titreTextView;
        ImageView miniatureImageView;

        public PublicationViewHolder(View v) {
            super(v);
            this.titreTextView = v.findViewById(R.id.titrePublicationTextView);
            this.miniatureImageView = v.findViewById(R.id.miniaturePublicationImageView);
        }
    }


}