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
        LinkedList<AppAction> undoActivityList = getUndoActivityList();
        LinkedList<AppAction> redoActivityList = getRedoActivityList();
        AppAction action = redoActivityList.pollLast();

        if (action != null) {
            undoActivityList.add(action);
            action.execute();
        }

        if (!redoActivityList.isEmpty()) {
            return new StateEnableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
        }
        else {
            return new StateEnableUndoDisableRedo(getUndoActivityList(), getRedoActivityList());
        }
    }
}