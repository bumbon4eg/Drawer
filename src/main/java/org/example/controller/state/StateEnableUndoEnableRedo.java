package org.example.controller.state;

import org.example.controller.actions.AppAction;

import java.util.LinkedList;

public class StateEnableUndoEnableRedo extends UndoRedoState {
    protected StateEnableUndoEnableRedo(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivityList) {
        super(undoActivityList, redoActivityList);
    }

    @Override
    public UndoRedoState undo() {
        if (!getUndoActivityList().isEmpty()) {
            AppAction action = getUndoActivityList().removeLast();
            action.unexecute();
            getRedoActivityList().add(action);

            if (getUndoActivityList().isEmpty()) {
                return new StateDisableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
            } else {
                return this;
            }
        }
        return this;
    }

    @Override
    public UndoRedoState redo() {
        if (!getRedoActivityList().isEmpty()) {
            AppAction action = getRedoActivityList().removeLast();
            action.execute();
            getUndoActivityList().add(action);

            if (getRedoActivityList().isEmpty()) {
                return new StateEnableUndoDisableRedo(getUndoActivityList(), getRedoActivityList());
            } else {
                return this;
            }
        }
        return this;
    }
}