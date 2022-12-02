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
