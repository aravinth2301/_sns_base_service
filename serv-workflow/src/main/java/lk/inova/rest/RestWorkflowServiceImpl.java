package lk.inova.rest;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.TaskInfoQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lk.inova.dto.WFRequestDTO;
import lk.inova.dto.WFRoleDTO;
import lk.inova.dto.WFZoneDTO;
import lk.inova.util.Variables;
import lk.inova.util.WF_REST_URL;

@RestController
public class RestWorkflowServiceImpl {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	@RequestMapping(value = WF_REST_URL.CREATE_PROCESS, method = { RequestMethod.POST })
	public @ResponseBody Object createProcess(@RequestBody WFRequestDTO requestDTO) {

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("examApprovalGroup",
				examProcessGroupName(requestDTO.getEscalateTo().getRole(), requestDTO.getEscalateTo().getZone()));
		ProcessInstance temp = runtimeService.startProcessInstanceByKey("cert-approval", variables);
		return "created";
	}

	@RequestMapping(value = WF_REST_URL.VIEW_PROCESSES, method = { RequestMethod.POST })
	public  Object viewProcesses(@RequestBody WFRequestDTO requestDTO) {
		//return taskService.createTaskQuery().taskCandidateGroupIn(Arrays.asList(examProcessGroupName(requestDTO.getEscalateTo().getRole(), requestDTO.getEscalateTo().getZone())));
		//return taskService.createTaskQuery().taskCandidateGroupIn(Arrays.asList("R-D_Z-01"));
		TaskInfoQueryWrapper taskInfoQueryWrapper = new TaskInfoQueryWrapper(taskService.createTaskQuery());
		List<?> temp = taskInfoQueryWrapper.getTaskInfoQuery().taskCandidateGroup("R-D_Z-01").list();
		return temp;
	}

	@RequestMapping(value = WF_REST_URL.FINISH_PROCESS, method = { RequestMethod.POST })
	public @ResponseBody Object finishProcesses(@RequestBody WFRequestDTO requestDTO) {
		Map<String, Object> variables = new HashMap<>();
		variables.put(Variables.ZONE.getKey(), requestDTO.getModifiedBy().getZone().getCode());
		variables.put(Variables.TASK_STATUS.getKey(), requestDTO.getTask().getStatus());
		taskService.complete(requestDTO.getTask().getTaskId(), variables);
		return "";

	}

	@RequestMapping(value = WF_REST_URL.VIEW_FINISH_PROCESSES, method = { RequestMethod.POST })
	public @ResponseBody Object viewFinishProcesses(@RequestBody WFRequestDTO requestDTO) {
		return historyService.createHistoricProcessInstanceQuery().finished()
				.variableValueEquals(Variables.ZONE.getKey(), requestDTO.getEscalateTo().getZone().getCode())
				.variableValueEqualsIgnoreCase(Variables.TASK_STATUS.getKey(), requestDTO.getTask().getStatus()).list();

	}

	@RequestMapping(value = "/history", method = { RequestMethod.POST })
	public Object getHistory() {
		return historyService.createHistoricProcessInstanceQuery().finished().variableValueEquals("zone", "Z03").list();
	}

	private String examProcessGroupName(WFRoleDTO roleDTO, WFZoneDTO zoneDTO) {
		return roleDTO.getCode() + "_" + zoneDTO.getCode();
	}

}