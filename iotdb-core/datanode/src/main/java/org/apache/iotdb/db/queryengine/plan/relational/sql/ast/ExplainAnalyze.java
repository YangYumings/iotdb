/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.iotdb.db.queryengine.plan.relational.sql.ast;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static java.util.Objects.requireNonNull;

public class ExplainAnalyze extends Statement {

  private final Statement statement;
  private final boolean verbose;

  public ExplainAnalyze(Statement statement, boolean verbose) {
    super(null);
    this.statement = requireNonNull(statement, "statement is null");
    this.verbose = verbose;
  }

  public ExplainAnalyze(NodeLocation location, boolean verbose, Statement statement) {
    super(requireNonNull(location, "location is null"));
    this.statement = requireNonNull(statement, "statement is null");
    this.verbose = verbose;
  }

  public Statement getStatement() {
    return statement;
  }

  public boolean isVerbose() {
    return verbose;
  }

  @Override
  public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
    return visitor.visitExplainAnalyze(this, context);
  }

  @Override
  public List<Node> getChildren() {
    return ImmutableList.of(statement);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statement, verbose);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    ExplainAnalyze o = (ExplainAnalyze) obj;
    return Objects.equals(statement, o.statement);
  }

  @Override
  public String toString() {
    return toStringHelper(this).add("statement", statement).add("verbose", verbose).toString();
  }

  @Override
  public boolean isQuery() {
    return true;
  }
}
