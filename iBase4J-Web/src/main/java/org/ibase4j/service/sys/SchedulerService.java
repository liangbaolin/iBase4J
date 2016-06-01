package org.ibase4j.service.sys;

import java.util.List;
import java.util.Map;

import org.ibase4j.core.config.Resources;
import org.ibase4j.mybatis.generator.model.TaskFireLog;
import org.ibase4j.mybatis.generator.model.TaskGroup;
import org.ibase4j.mybatis.generator.model.TaskScheduler;
import org.ibase4j.scheduler.TaskScheduled;
import org.ibase4j.scheduler.provider.SchedulerProvider;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:20
 */
@Service
public class SchedulerService {
	@Reference // 依赖调度服务
	private SchedulerProvider schedulerProvider;

	public List<TaskScheduled> getAllJobDetail() {
		return schedulerProvider.getAllTaskDetail();
	}

	public boolean execTask(String taskGroup, String taskName) {
		Assert.notNull(taskGroup, Resources.getMessage("TASKGROUP_IS_NULL"));
		Assert.notNull(taskName, Resources.getMessage("TASKNAME_IS_NULL"));
		return schedulerProvider.execTask(taskName, taskGroup);
	}

	public boolean openTask(String taskGroup, String taskName) {
		Assert.notNull(taskGroup, Resources.getMessage("TASKGROUP_IS_NULL"));
		Assert.notNull(taskName, Resources.getMessage("TASKNAME_IS_NULL"));
		return schedulerProvider.openCloseTask(taskGroup, taskName, "start");
	}

	public boolean closeTask(String taskGroup, String taskName) {
		Assert.notNull(taskGroup, Resources.getMessage("TASKGROUP_IS_NULL"));
		Assert.notNull(taskName, Resources.getMessage("TASKNAME_IS_NULL"));
		return schedulerProvider.openCloseTask(taskGroup, taskName, "stop");
	}

	public PageInfo<TaskGroup> queryGroup(Map<String, Object> params) {
		return schedulerProvider.queryGroup(params);
	}

	public PageInfo<TaskScheduler> queryScheduler(Map<String, Object> params) {
		return schedulerProvider.queryScheduler(params);
	}

	public PageInfo<TaskFireLog> queryLog(Map<String, Object> params) {
		return schedulerProvider.queryLog(params);
	}
}
