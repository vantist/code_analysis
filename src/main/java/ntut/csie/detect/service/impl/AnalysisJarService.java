package ntut.csie.detect.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ntut.csie.detect.component.AnalysisFile;
import ntut.csie.detect.service.AnalysisService;

@Service("AnalysisJarService")
public class AnalysisJarService implements AnalysisService, Runnable {
	private List<AnalysisFile> files = new ArrayList<AnalysisFile>();

	@Override
	public Boolean check() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void analysis() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		
	}
}
