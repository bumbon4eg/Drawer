package org.example.controller.state;

import lombok.Setter;
import org.example.controller.actions.AppAction;
import org.example.view.menu.CommandActionListener;

import java.util.LinkedList;

public class UndoMachine {
    private UndoRedoState undoRedoState;
    @Setter
    private CommandActionListener undoActionListener;
    @Setter
    private CommandActionListener redoActionListener;

    public UndoMachine() {
        LinkedList<AppAction> undoList = new LinkedList<>();
        LinkedList<AppAction> redoList = new LinkedList<>();
        undoRedoState = new StateDisableUndoDisableRedo(undoList, redoList);
        updateButtons();
    }

    public void executeRedo() {
        undoRedoState = undoRedoState.redo();
    }

    public void executeUndo() {
        undoRedoState = undoRedoState.undo();
    }

    public boolean isEnableUndo() {
        return !undoRedoState.getUndoActivityList().isEmpty();
    }


    public boolean isEnableRedo() {
        return !undoRedoState.getRedoActivityList().isEmpty();
    }

    public void add(AppAction action) {
        if (!undoRedoState.getRedoActivityList().isEmpty()) {
            undoRedoState.clearHistory();
        }

        undoRedoState.addAction(action);

        updateStateAfterAction();
        updateButtons();
    }

    public void updateButtons() {
        if (undoActionListener != null) {
            undoActionListener.setEnabled(isEnableUndo());
        }
        if (redoActionListener != null) {
            redoActionListener.setEnabled(isEnableRedo());
        }
    }

    private void updateStateAfterAction() {
        boolean canUndo = isEnableUndo();
        boolean canRedo = isEnableRedo();

        LinkedList<AppAction> undoList = undoRedoState.getUndoActivityList();
        LinkedList<AppAction> redoList = undoRedoState.getRedoActivityList();

        if (canUndo && canRedo) {
            undoRedoState = new StateEnableUndoEnableRedo(undoList, redoList);
        } else if (canUndo) {
            undoRedoState = new StateEnableUndoDisableRedo(undoList, redoList);
        } else if (canRedo) {
            undoRedoState = new StateDisableUndoEnableRedo(undoList, redoList);
        } else {
            undoRedoState = new StateDisableUndoDisableRedo(undoList, redoList);
        }
    }
}