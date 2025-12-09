package org.example.controller.state;

import org.example.controller.actions.AppAction;

import java.util.LinkedList;

public class StateDisableUndoEnableRedo extends UndoRedoState{

    protected StateDisableUndoEnableRedo(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        return this;
    }

    @Override
    public UndoRedoState redo() {
        if (!getRedoActivityList().isEmpty()) {
            AppAction action = getRedoActivityList().removeLast();
            action.execute();
            getUndoActivityList().add(action);

            if (getRedoActivityList().isEmpty()) {
                return new StateEnableUndoDisableRedo(
                        getUndoActivityList(), getRedoActivityList());
            } else {
                return new StateEnableUndoEnableRedo(
                        getUndoActivityList(), getRedoActivityList());
            }
        }
        return this;
    }
}