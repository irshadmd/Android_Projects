/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.plugins.ide.internal.tooling.model;

import java.io.Serializable;
import java.util.List;

/**
 * Implementation of {@link org.gradle.tooling.model.gradle.BuildInvocations}
 */
public class DefaultBuildInvocations implements Serializable {
    private List<? extends LaunchableGradleTaskSelector> selectors;
    private List<? extends LaunchableGradleTask> tasks;

    public DefaultBuildInvocations setSelectors(List<? extends LaunchableGradleTaskSelector> selectors) {
        this.selectors = selectors;
        return this;
    }

    public List<? extends LaunchableGradleTaskSelector> getTaskSelectors() {
        return selectors;
    }

    public DefaultBuildInvocations setTasks(List<? extends LaunchableGradleTask> tasks) {
        this.tasks = tasks;
        return this;
    }

    public List<? extends LaunchableGradleTask> getTasks() {
        return tasks;
    }
}
