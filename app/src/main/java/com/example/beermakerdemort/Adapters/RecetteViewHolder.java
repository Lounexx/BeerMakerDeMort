package com.example.beermakerdemort.Adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.beermakerdemort.Objects.Recette;
import com.example.beermakerdemort.R;

import java.lang.ref.WeakReference;

public class RecetteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView textView;
    private Button button;
    private WeakReference<MyRecyclerViewAdapter.Listener> weakReference;
    public RecetteViewHolder(View itemView) {
        super(itemView);
        this.textView = itemView.findViewById(R.id.tv_card);
        this.button = itemView.findViewById(R.id.btn_card);
    }


    public void bind(Recette recette, int position,MyRecyclerViewAdapter.Listener callback) {
        textView.setText("Recette n°"+position);
        button.setText("Accéder");
        button.setOnClickListener(this);
        this.weakReference = new WeakReference<MyRecyclerViewAdapter.Listener>(callback);
    }

    @Override
    public void onClick(View v) {
        MyRecyclerViewAdapter.Listener callback = weakReference.get();
        if(callback != null)callback.onClickButton(getAdapterPosition());
    }
}
