/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.hive.ptest;

import java.util.List;

import org.apache.hive.ptest.ssh.RSyncCommand;
import org.apache.hive.ptest.ssh.RSyncCommandExecutor;

import com.google.common.collect.Lists;

public class MockRSyncCommandExecutor extends RSyncCommandExecutor {
  protected Exception exception;
  protected final List<String> commands = Lists.newArrayList();
  public List<String> getCommands() {
    return commands;
  }
  public void setException(Exception exception) {
    this.exception = exception;
  }
  @Override
  public void execute(RSyncCommand command) {
    commands.add(command.getLocalFile() + " " + command.getRemoteFile());
    command.setExitCode(0);
    command.setOutput("");
    if(exception != null) {
      command.setException(exception);
    }
  }
}
