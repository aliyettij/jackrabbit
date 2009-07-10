/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jackrabbit.core.id;

import org.apache.jackrabbit.uuid.UUID;

/**
 * Node identifier. An instance of this class identifies a node using its UUID.
 * Once created a node identifier instance is immutable.
 */
public class NodeId extends ItemId implements Comparable<NodeId> {

    /** Serial version UID of this class. */
    static final long serialVersionUID = 7380115476447060008L;

    /** UUID of the identified node */
    private final UUID uuid;

    /** the precalculated hashcode */
    private final int hashCode;

    /**
     * Creates a node identifier instance for the identified node.
     *
     * @param uuid node UUID
     */
    public NodeId(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("uuid can not be null");
        }
        this.uuid = uuid;
        this.hashCode = uuid.hashCode();
    }

    /**
     * Creates a node identifier from the given UUID string.
     *
     * @param uuid UUID string
     * @throws IllegalArgumentException if the UUID string is invalid
     */
    public NodeId(String uuid) throws IllegalArgumentException {
        this(new UUID(uuid));
    }

    public NodeId(byte[] bytes) {
        this(new UUID(bytes));
    }

    public NodeId(long msb, long lsb) {
        this(new UUID(msb, lsb));
    }

    /**
     * Returns <code>true</code> as this class represents a node identifier,
     * not a property identifier.
     *
     * @return always <code>true</code>
     * @see ItemId#denotesNode()
     */
    public boolean denotesNode() {
        return true;
    }

    /**
     * Returns the UUID of the identified node.
     *
     * @return node UUID
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * Returns a <code>NodeId</code> holding the value of the specified
     * string. The string must be in the format returned by the
     * <code>NodeId.toString()</code> method.
     *
     * @param s a <code>String</code> containing the <code>NodeId</code>
     *          representation to be parsed.
     * @return the <code>NodeId</code> represented by the argument
     * @throws IllegalArgumentException if the specified string can not be parsed
     *                                  as a <code>NodeId</code>.
     * @see #toString()
     */
    public static NodeId valueOf(String s) throws IllegalArgumentException {
        if (s == null) {
            throw new IllegalArgumentException("invalid NodeId literal");
        }
        return new NodeId(new UUID(s));
    }

    /**
     * Returns a (new) array containing the raw bytes that make up this UUID.
     *
     * @return raw bytes of the UUID
     */
    public byte[] getRawBytes() {
        return uuid.getRawBytes();
    }

    /**
     * Returns the most significant bits of the UUID.
     *
     * @return most significant 64 bits
     */
    public long getMostSignificantBits() {
        return uuid.getMostSignificantBits();
    }

    /**
     * Returns the least significant bits of the UUID.
     *
     * @return least significant 64 bits
     */
    public long getLeastSignificantBits() {
        return uuid.getLeastSignificantBits();
    }

    //----------------------------------------------------------< Comparable >

    /**
     * Compares this node id to the given other identifier.
     *
     * @param that the other identifier for the comparison
     * @return result of comparison
     */
    public int compareTo(NodeId that) {
        return uuid.compareTo(that.uuid);
    }

    //-------------------------------------------< java.lang.Object overrides >
    /**
     * {@inheritDoc}
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NodeId) {
            return uuid.equals(((NodeId) obj).uuid);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * Returns the same as <code>this.getUUID().toString()</code>
     */
    public String toString() {
        return uuid.toString();
    }

    /**
     * {@inheritDoc}
     *
     * Returns the same as <code>this.getUUID().hashCode()</code>
     */
    public int hashCode() {
        return hashCode;
    }

}