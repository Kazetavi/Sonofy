package github.com.kazetavi.sonofy.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import github.com.kazetavi.sonofy.ui.addpublication.AddPublicationActivity;
import github.com.kazetavi.sonofy.R;
import github.com.kazetavi.sonofy.data.model.Publication;
import github.com.kazetavi.sonofy.ui.search.SearchActivity;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton newPublicationButton, search_btn;
    private RecyclerView publicationRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    Button sortTitre;
    Button sortDate;
    Button sortLike;
    Button archive_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sortTitre = findViewById(R.id.sortByTitre);
        sortDate = findViewById(R.id.sortByDate);
        sortLike = findViewById(R.id.sortByLike);
        archive_btn = findViewById(R.id.btn_sup);

        newPublicationButton = findViewById(R.id.newPublicationButton);
        publicationRecyclerView = findViewById(R.id.publicationRecyclerView);

        search_btn = findViewById(R.id.search_activity);

        layoutManager = new LinearLayoutManager(this);
        publicationRecyclerView.setLayoutManager(layoutManager);

        final MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);


        mainViewModel.getPublications().observe(this, new Observer<List<Publication>>() {
            @Override
            public void onChanged(List<Publication> publications) {
                adapter = new PublicationAdapter(publications);
                publicationRecyclerView.setAdapter(adapter);
            }
        });

        mainViewModel.loadPublicationsDate();

        sortTitre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.loadPublicationsTitre();
            }
        });

        sortDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.loadPublicationsDate();
            }
        });

        sortLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.loadPublicationsLike();
            }
        });

        archive_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.loadPublications();
            }
        });

        newPublicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddPublicationActivity.class);
                startActivity(intent);
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            }
        });


    }


}