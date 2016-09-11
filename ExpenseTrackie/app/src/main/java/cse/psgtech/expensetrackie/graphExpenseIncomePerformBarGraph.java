package cse.psgtech.expensetrackie;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
public class graphExpenseIncomePerformBarGraph  {

    public Intent getIntent(Context context){

        int y[] = new int[4];
        y[0] = graphExpenseIncomeActivity.totexp;
        y[1] = graphExpenseIncomeActivity.totinc;
        y[2] = graphExpenseIncomeActivity.totbal;

        CategorySeries series = new CategorySeries("Bar1");
        for(int i=0; i < y.length; i++){
            series.add("Bar"+(i+1),y[i]);
        }

        XYMultipleSeriesDataset dataSet = new XYMultipleSeriesDataset();  // collection of series under one object.,there could any
        dataSet.addSeries(series.toXYSeries());                            // number of series

        //customization of the chart

        XYSeriesRenderer renderer = new XYSeriesRenderer();     // one renderer for one series
        renderer.setColor(Color.YELLOW);
        renderer.setDisplayChartValues(true);
        renderer.setChartValuesSpacing((float) 1.0d);
        renderer.setLineWidth((float) 2.5d);


        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();   // collection multiple values for one renderer or series
        mRenderer.addSeriesRenderer(renderer);
        mRenderer.setChartTitle("Expense Chart");
//        mRenderer.setXTitle("xValues");
        mRenderer.setYTitle("Amount");
        mRenderer.setZoomButtonsVisible(true);
        mRenderer.setShowLegend(true);
        mRenderer.setShowGridX(true);      // this will show the grid in  graph
        mRenderer.setShowGridY(true);
//        mRenderer.setAntialiasing(true);
        mRenderer.setBarSpacing(.5);   // adding spacing between the line or stacks
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.WHITE);
        mRenderer.setXAxisMin(0);
//        mRenderer.setYAxisMin(.5);
        mRenderer.setXAxisMax(3);
        mRenderer.setYAxisMax(10000);
//
        mRenderer.setXLabels(0);
        mRenderer.addXTextLabel(1,"EXPENSE");
        mRenderer.addXTextLabel(2,"INCOME");
        mRenderer.addXTextLabel(3,"BALANCE");

        mRenderer.setPanEnabled(true, true);    // will fix the chart position
        Intent intent = ChartFactory.getBarChartIntent(context, dataSet, mRenderer,Type.DEFAULT);

        return intent;
    }
}
