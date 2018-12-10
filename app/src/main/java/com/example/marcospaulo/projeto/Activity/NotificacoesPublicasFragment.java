package com.example.marcospaulo.projeto.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marcospaulo.projeto.R;

public class NotificacoesPublicasFragment extends Fragment {
    View view;

    public NotificacoesPublicasFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notificacoes_publicas, container, false);

        return view;
    }
}
