package com.cw.controller;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;

import java.util.Set;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CustomListView<T> extends ListView<T> {
    private ListChangeListener listChangeListener = null;

    public CustomListView(ObservableList<T> items) {
        super(items);
    }

    public void setHScrollBarEnabled(boolean value) {
        setScrollBarEnabled(value, Orientation.HORIZONTAL);
    }

    private void setScrollBarEnabled(boolean value, Orientation orientation) {
        Set<Node> set = this.lookupAll("VirtualScrollBar");

        for (Node n : set) {
            ScrollBar bar = (ScrollBar) n;
            if (bar.getOrientation() == orientation) {
                if (value) {
                    bar.setVisible(true);
                    bar.setDisable(false);
                    bar.setStyle("-fx-opacity: 100%");
                } else {
                    bar.setVisible(false);
                    bar.setDisable(true);
                    bar.setStyle("-fx-opacity: 0%");
                }
            }
        }
    }

    public void setVScrollBarEnabled(boolean value) {
        setScrollBarEnabled(value, Orientation.VERTICAL);
    }

    public void setAutoScrollEnabled(boolean value) {
        if (value) {
            listChangeListener = c -> {
                c.next();
                final int size = getItems().size();
                if (size > 0) {
                    scrollTo(size - 1);
                }
            };
            getItems().addListener(listChangeListener);
        } else {
            if (listChangeListener != null) {
                getItems().removeListener(listChangeListener);
                listChangeListener = null;
            }
        }
    }
}