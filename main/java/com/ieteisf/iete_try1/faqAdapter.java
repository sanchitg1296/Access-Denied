package com.ieteisf.iete_try1;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class faqAdapter extends FirebaseRecyclerAdapter<faq,faqAdapter.myviewholder>
{
    public faqAdapter(@NonNull FirebaseRecyclerOptions<faq> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull faq model) {

        holder.ques.setText(model.getQuestion());
        holder.ans.setText(model.getAnswer());
        ExpandCard(holder);


    }

    private void ExpandCard(myviewholder holder) {

        try {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {

                    if(holder.ans.getVisibility() == View.VISIBLE){
                        //TransitionManager.beginDelayedTransition((ViewGroup) holder.itemView,new AutoTransition());
                        holder.ans.setVisibility(View.GONE);
                    }
                    else {

                        //TransitionManager.beginDelayedTransition((ViewGroup) holder.itemView, new AutoTransition());
                        holder.ans.setVisibility(View.VISIBLE);
                    }

                }
            });
        }catch (Exception e){
            Toast.makeText(holder.itemView.getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq,parent,false);
        //return new myviewholder(view);

        return new faqAdapter.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView ques,ans;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            ques = itemView.findViewById(R.id.ques);
            ans = itemView.findViewById(R.id.hidden_view);
        }
    }

}