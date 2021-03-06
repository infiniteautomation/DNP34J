package br.org.scadabr.dnp34j.master.session.database;

import br.org.scadabr.dnp34j.master.common.DataMapFeatures;

public class DataElement implements DataMapFeatures {
    private int index;
    /**
     * This is the generic group like 2x or 3x not the specific group of the data type.  This is internal to DNP34J
     */
    private int group;
    private byte specificGroup;
    private byte variation;
    private long timestamp;
    private int quality;
    private Object value;
    private byte controlStatus;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public byte getSpecificGroup() {
        return specificGroup;
    }

    public void setSpecificGroup(byte specificGroup) {
        this.specificGroup = specificGroup;
    }

    public byte getVariation() {
        return variation;
    }

    public void setVariation(byte variation) {
        this.variation = variation;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public byte getControlStatus() {
        return controlStatus;
    }

    public void setControlStatus(byte controlStatus) {
        this.controlStatus = controlStatus;
    }

    /**
     * Does this group/variation support quality data?
     * @return
     */
    public boolean supportsQuality() {
        switch(specificGroup) {
            case BINARY_INPUT_STATIC:
                //Binary Inputs
                switch(variation) {
                    case 2:
                        return true;
                    case 1:
                    default:
                        return false;
                }
            case BINARY_INPUT_EVENT:
                //Binary input events
                switch(variation) {
                    case 1:
                    case 2:
                    case 3:
                        return true;
                    default:
                        return false;
                }
            case BINARY_OUTPUT_STATIC:
                //Binary outputs
                switch(variation) {
                    case 2:
                        return true;
                    case 1:
                    default:
                        return false;
                }
            case BINARY_OUTPUT_EVENT:
                //Binary output events
                switch(variation) {
                    case 1:
                    case 2:
                        return true;

                    default:
                        return false;
                }
            case COUNTER_STATIC:
                //Counters
                switch(variation) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        return true;
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    default:
                        return false;
                }
            case FROZEN_COUNTER:
                //Frozen Counters
                switch(variation) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        return true;
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    default:
                        return false;
                }
            case COUNTER_EVENT:
                //Counter events
                switch(variation) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        return true;
                    default:
                        return false;
                }
            case FROZEN_COUNTER_EVENT:
                //Frozen Counter events
                switch(variation) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        return true;
                    default:
                        return false;
                }
            case ANALOG_INPUT_STATIC:
                //Analog Inputs
                switch(variation) {
                    case 1:
                    case 2:
                    case 5:
                    case 6:
                        return true;
                    case 3:
                    case 4:
                    default:
                        return false;
                }
            case FROZEN_ANALOG_INPUT:
                //Frozen analog Inputs
                switch(variation) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 7:
                    case 8:
                        return true;
                    case 5:
                    case 6:
                    default:
                        return false;
                }
            case ANALOG_INPUT_EVENT:
                //Analog Input Events
                switch(variation) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        return true;
                    default:
                        return false;
                }
            case FROZEN_ANALOG_INPUT_EVENT:
                //Frozen Analog Input Events
                switch(variation) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        return true;
                    default:
                        return false;
                }
            case ANALOG_INPUT_REPORTING_DEADBAND:
                //Analog Input Reporting Deadband
                switch(variation) {
                    case 1:
                    case 2:
                    case 3:
                    default:
                        return false;
                }
            case ANALOG_OUTPUT_STATIC:
                //Analog output status
                switch(variation) {
                    case 1:
                    case 2:
                    case 3:
                        return true;
                    case 4:
                    default:
                        return false;
                }
            case ANALOG_OUTPUT_EVENTS:
                //Analog output events
                switch(variation) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    default:
                        return false;
                }
            default:
                return false;
        }
    }

    public boolean isUnreliable() {
        switch(group) {
            case DataMapFeatures.ANA_IN:
            case DataMapFeatures.ANA_OUT:
                return !isOnline() || isRestart() || isCommsLost() || isRemoteForced() || isLocalForced() || isOverRange() || isReferenceError();
            case DataMapFeatures.COUNTER:
                return !isOnline() || isCommsLost() || isRemoteForced() || isLocalForced() || isRollover() || isDiscontinuity();
            case DataMapFeatures.BIN_IN:
                return !isOnline() || isCommsLost() || isRemoteForced() || isLocalForced() || isChatterFilter();
            case DataMapFeatures.BIN_OUT:
                return !isOnline() || isCommsLost() || isRemoteForced() || isLocalForced();
            default:
                return false;
        }
    }

    public boolean isOnline() {
        return (((byte)quality) & 0b00000001) > 0;
    }

    public boolean isRestart() {
        return (((byte)quality) & 0b00000010) > 0;
    }

    public boolean isCommsLost() {
        return (((byte)quality) & 0b00000100) > 0;
    }

    public boolean isRemoteForced() {
        return (((byte)quality) & 0b00001000) > 0;
    }

    public boolean isLocalForced() {
        return (((byte)quality) & 0b00010000) > 0;
    }

    /**
     * For Analog I/O
     * @return
     */
    public boolean isOverRange() {
        return (((byte)quality) & 0b00100000) > 0;
    }

    /**
     * For Analog I/O
     * @return
     */
    public boolean isReferenceError() {
        return (((byte)quality) & 0b01000000) > 0;
    }

    /**
     * For Counter
     * @return
     */
    public boolean isRollover() {
        return (((byte)quality) & 0b00100000) > 0;
    }

    /**
     * For Counter
     * @return
     */
    public boolean isDiscontinuity() {
        return (((byte)quality) & 0b01000000) > 0;
    }

    /**
     * For Binary Input
     * @return
     */
    public boolean isChatterFilter() {
        return (((byte)quality) & 0b00100000) > 0;
    }

    //Control status fields
    public boolean isTimeout() {
        return controlStatus == 1;
    }

    public boolean noSelect() {
        return controlStatus == 2;
    }

    public boolean formatError() {
        return controlStatus == 3;
    }

    public boolean notSupported() {
        return controlStatus == 4;
    }

    public boolean alreadyActive() {
        return controlStatus == 5;
    }

    public boolean hardwareError() {
        return controlStatus == 6;
    }

    public boolean localMode() {
        return controlStatus == 7;
    }

    public boolean tooManyObjects() {
        return controlStatus == 8;
    }

    public boolean notAuthorized() {
        return controlStatus == 9;
    }

    public boolean automationInhibit() {
        return controlStatus == 10;
    }

    public boolean processingLimited() {
        return controlStatus == 11;
    }

    public boolean outOfRange() {
        return controlStatus == 12;
    }

    public boolean notParticipating() {
        return controlStatus == 126;
    }

    public boolean hasControlStatus() {
        return controlStatus > 0;
    }

    @Override
    public String toString() {
        return "DataElement [index=" + index + ", group=" + group + ", timestamp=" + timestamp + ", quality=" + quality
                + ", value=" + value + "]";
    }
}
