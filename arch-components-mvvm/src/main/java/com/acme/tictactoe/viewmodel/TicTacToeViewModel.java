package com.acme.tictactoe.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;

import com.acme.tictactoe.model.Board;
import com.acme.tictactoe.model.Player;

public class TicTacToeViewModel extends ViewModel {

    private Board model;

    public final ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    public final ObservableField<String> winner = new ObservableField<>();

    public TicTacToeViewModel() {
        model = new Board();
    }

    public void onResetSelected() {
        model.restart();
        winner.set(null);
        cells.clear();
    }

    public void onClickedCellAt(int row, int col) {
        Player playerThatMoved = model.mark(row, col);
        cells.put("" + row + col, playerThatMoved == null ? null : playerThatMoved.toString());
        winner.set(model.getWinner() == null ? null : model.getWinner().toString());
    }

}
