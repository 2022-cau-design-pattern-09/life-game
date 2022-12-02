package com.holub.life;

public class SurroundingCells {
    
    private Cell north;
    private Cell south;
    private Cell east;
    private Cell west;
    private Cell northeast;
    private Cell northwest;
    private Cell southeast;
    private Cell southwest;

    public Cell getNorth() { return this.north; }
    public Cell getSouth() { return this.south; }
    public Cell getEast() { return this.east; }
    public Cell getWest() { return this.west; }
    public Cell getNorthEast() { return this.northeast; }
    public Cell getNorthWest() { return this.northwest; }
    public Cell getSouthEast() { return this.southeast; }
    public Cell getSouthWest() { return this.southwest; }

    private SurroundingCells (SurroundingCellsBuilder builder) {
        this.north = builder.north;
        this.south = builder.south;
        this.east = builder.east;
        this.west = builder.west;
        this.northeast = builder.northeast;
        this.northwest = builder.northwest;
        this.southeast = builder.southeast;
        this.southwest = builder.southwest;
    }

    public SurroundingCells() {
        this.north = Cell.DUMMY;
        this.south = Cell.DUMMY;
        this.east = Cell.DUMMY;
        this.west = Cell.DUMMY;
        this.northeast = Cell.DUMMY;
        this.northwest = Cell.DUMMY;
        this.southeast = Cell.DUMMY;
        this.southwest = Cell.DUMMY;
    } 

    public Boolean hasChangedStateSurroundingCells () {
        return north.isDisruptiveTo().the(Direction.SOUTH) 
            || south.isDisruptiveTo().the(Direction.NORTH)
            || east.isDisruptiveTo().the(Direction.WEST)
            || west.isDisruptiveTo().the(Direction.EAST)
            || northeast.isDisruptiveTo().the(Direction.SOUTHWEST)
            || northwest.isDisruptiveTo().the(Direction.SOUTHEAST)
            || southeast.isDisruptiveTo().the(Direction.NORTHWEST)
            || southwest.isDisruptiveTo().the(Direction.NORTHEAST);
    }
    public static class SurroundingCellsBuilder {

        private Cell north;
        private Cell south;
        private Cell east;
        private Cell west;
        private Cell northeast;
        private Cell northwest;
        private Cell southeast;
        private Cell southwest;

        SurroundingCellsBuilder() {

        }        
        
        public SurroundingCellsBuilder setNorth(Cell north) {
            this.north = north;
            return this;
        }

        public SurroundingCellsBuilder setSouth(Cell south) {
            this.south = south;
            return this;
        }

        public SurroundingCellsBuilder setEast(Cell east) {
            this.east = east;
            return this;
        }

        public SurroundingCellsBuilder setWest(Cell west) {
            this.west = west;
            return this;
        }

        public SurroundingCellsBuilder setNorthEast(Cell northEast) {
            this.northeast = northEast;
            return this;
        }

        public SurroundingCellsBuilder setNorthWest(Cell northWest) {
            this.northwest = northWest;
            return this;
        }

        public SurroundingCellsBuilder setSouthEast(Cell southEast) {
            this.southeast = southEast;
            return this;
        }

        public SurroundingCellsBuilder setSouthWest(Cell southWest) {
            this.southwest = southWest;
            return this;
        }

        public SurroundingCells build() {
            return new SurroundingCells(this);
        }
    }

}
