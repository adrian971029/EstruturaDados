package com.adrian971029.estruturadados.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.adrian971029.estruturadados.R;
import com.adrian971029.estruturadados.model.Processo;

import butterknife.ButterKnife;

public class ProcessoAdapter extends ListAdapter<Processo, ProcessoAdapter.ProcessoHolder> {

    private OnItemClickListener listener;

    public ProcessoAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Processo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Processo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Processo oldItem, @NonNull Processo newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Processo oldItem, @NonNull Processo newItem) {
            return oldItem.getNomeProcesso().equals(newItem.getNomeProcesso()) &&
                    oldItem.getId() == newItem.getId();
        }
    };

    @NonNull
    @Override
    public ProcessoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_processos, parent, false);
        ProcessoHolder noteHolder = new ProcessoHolder(itemView);
        return noteHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProcessoHolder holder, int position) {
        Processo processoAtual = getItem(position);
        holder.tvNomeProcesso.setText(processoAtual.getNomeProcesso());
        holder.tvIdProcesso.append(" " + processoAtual.getId());
    }

    public Processo getProcessoAt(int position) {
        return getItem(position);
    }

    class ProcessoHolder extends RecyclerView.ViewHolder {
        private TextView tvNomeProcesso;
        private TextView tvIdProcesso;

        public ProcessoHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Processo processo);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
