package uz.gita.mobilebanking.ui.widgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import uz.gita.mobilebanking.MainActivity
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.source.remote.currency.CurrencyThread
import uz.gita.mobilebanking.utils.toLog

/**
 * Implementation of App Widget functionality.
 */
class ExchangeRateWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }


    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    /* override fun onAppWidgetOptionsChanged(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)

        updateAppWidget(context, appWidgetManager, appWidgetId)
        logger("onAppWidgetOptionsChanged.appWidgetManager id = $appWidgetId")
    }*/
}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    val views = RemoteViews(context.packageName, R.layout.exchange_rate_widget)

    // onClick refresh
    val refreshIntent = Intent(context, ExchangeRateWidget::class.java)
    refreshIntent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
    refreshIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, intArrayOf(appWidgetId))
    val refreshPendingIntent = PendingIntent.getBroadcast(context, 0, refreshIntent, PendingIntent.FLAG_IMMUTABLE)
    views.setOnClickPendingIntent(R.id.refresh, refreshPendingIntent)

    toLog("UPDATE WIDGET id = $appWidgetId")

    // update data
    CurrencyThread { list ->
        if (list.isEmpty()) return@CurrencyThread

        toLog("CURRENCY THREAD ${list.size}")
        for (it in list) {
            if (it.Ccy == "USD") {
                views.setTextViewText(R.id.usd_cb, it.Rate)
                views.setTextViewText(R.id.usd_buy, "${(it.Rate.toFloat() + 20).toInt()}.00")
                views.setTextViewText(R.id.usd_sell, "${(it.Rate.toFloat() - 45).toInt()}.00")
            }

            if (it.Ccy == "EUR") {
                views.setTextViewText(R.id.eu_cb, it.Rate)
                views.setTextViewText(R.id.eu_buy, "${(it.Rate.toFloat() + 90).toInt()}.00")
                views.setTextViewText(R.id.eu_sell, "${(it.Rate.toFloat() - 35).toInt()}.00")
            }

            if (it.Ccy == "RUB") {
                views.setTextViewText(R.id.rub_cb, it.Rate)
                views.setTextViewText(R.id.rub_buy, "${(it.Rate.toFloat() + 12).toInt()}.00")
                views.setTextViewText(R.id.rub_sell, "${(it.Rate.toFloat() - 4).toInt()}.00")
                toLog("update widget data")
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
        }
    }


    // on click root
    val configIntent = Intent(context, MainActivity::class.java)
    val configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, PendingIntent.FLAG_IMMUTABLE)
    views.setOnClickPendingIntent(R.id.widget, configPendingIntent);

    // update
    appWidgetManager.updateAppWidget(appWidgetId, views)
}