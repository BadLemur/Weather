package com.example.weather.customStrategy;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;

import java.util.Iterator;
import java.util.List;

public class CustomStrategy extends AddToEndSingleStrategy {

    @Override
    public <View extends MvpView> void beforeApply(List<ViewCommand<View>> currentState, ViewCommand<View> incomingCommand) {

        Iterator<ViewCommand<View>> iterator = currentState.iterator();
        while (iterator.hasNext()) {
            ViewCommand<View> viewCommand = iterator.next();
            if (viewCommand.getTag().equals(incomingCommand.getTag()))
                iterator.remove();
        }
    }

    @Override
    public <View extends MvpView> void afterApply(List<ViewCommand<View>> currentState, ViewCommand<View> incomingCommand) {
    }
}
