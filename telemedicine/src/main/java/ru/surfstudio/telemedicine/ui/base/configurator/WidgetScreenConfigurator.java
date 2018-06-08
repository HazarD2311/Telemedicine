package ru.surfstudio.telemedicine.ui.base.configurator;

import ru.surfstudio.android.core.ui.activity.CoreActivityInterface;
import ru.surfstudio.android.mvp.widget.configurator.BaseWidgetViewConfigurator;
import ru.surfstudio.telemedicine.ui.base.dagger.activity.ActivityComponent;
import ru.surfstudio.telemedicine.ui.base.dagger.widget.WidgetScreenModule;

/**
 * Базовый конфигуратор для WidgetView
 */

public abstract class WidgetScreenConfigurator
        extends BaseWidgetViewConfigurator<ActivityComponent, WidgetScreenModule> {

    @Override
    protected WidgetScreenModule getWidgetScreenModule() {
        return new WidgetScreenModule(getPersistentScope());
    }

    @Override
    protected ActivityComponent getParentComponent() {
        return (ActivityComponent) ((CoreActivityInterface) getTargetWidgetView().getContext())
                .getPersistentScope()
                .getConfigurator()
                .getActivityComponent();
    }
}
