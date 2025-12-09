package org.example.controller.state;

import org.example.controller.actions.AppAction;

import java.util.LinkedList;

public class StateEnableUndoDisableRedo extends UndoRedoState {

    protected StateEnableUndoDisableRedo(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        if (!getUndoActivityList().isEmpty()) {
            AppAction action = getUndoActivityList().removeLast();
            action.unexecute();
            getRedoActivityList().add(action);

            if (getUndoActivityList().isEmpty()) {
                return new StateDisableUndoEnableRedo(
                        getUndoActivityList(), getRedoActivityList());
            } else {
                return new StateEnableUndoEnableRedo(
                        getUndoActivityList(), getRedoActivityList());
            }
        }
        return this;
    }

    @Override
    public UndoRedoState redo() {
        return this;
    }
}