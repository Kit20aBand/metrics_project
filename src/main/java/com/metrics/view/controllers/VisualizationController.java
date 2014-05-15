package com.metrics.view.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.annotation.Scope;

import com.metrics.persistence.model.Event;
import com.metrics.persistence.model.Project;
import com.metrics.persistence.service.IEventService;
import com.metrics.util.ThingsOverWhichIsWorking;

@Named
@Scope("request")
public class VisualizationController {

	private static final Log log = LogFactory
			.getLog(VisualizationController.class);

	@Inject
	private ThingsOverWhichIsWorking thingsOverWhichIsWorking;

	@Inject
	private IEventService eventService;

	private PieChartModel pieChart;

	private LineChartModel lineChart;

	private int number;

	@PostConstruct
	public void init() {
		final Project project = thingsOverWhichIsWorking.getActiveProject();
		final List<Event> selectedEvents = thingsOverWhichIsWorking
				.getEventsToVisualize();
		pieChart = new PieChartModel();
		for (final Event event : selectedEvents) {
			pieChart.set(event.getName(),
					eventService.getCountOfEvents(project, event.getName()));
		}
		final LineChartSeries series;
		/*
		 * for (final Event event : selectedEvents) { series = new
		 * LineChartSeries(); series.set(event.getDate(), y); }
		 */


	}

	public PieChartModel getChart() {
		return pieChart;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(final int number) {
		this.number = number;
	}


}
