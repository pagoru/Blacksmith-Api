package net.darkaqua.blacksmith.api.common.block.methods;

import net.darkaqua.blacksmith.api.common.block.IBlock;
import net.darkaqua.blacksmith.api.common.block.blockdata.IBlockData;
import net.darkaqua.blacksmith.api.common.entity.IEntity;
import net.darkaqua.blacksmith.api.common.entity.ILivingEntity;
import net.darkaqua.blacksmith.api.common.entity.IPlayer;
import net.darkaqua.blacksmith.api.common.inventory.IItemStack;
import net.darkaqua.blacksmith.api.common.util.Direction;
import net.darkaqua.blacksmith.api.common.util.vectors.Vect3d;
import net.darkaqua.blacksmith.api.common.util.WorldRef;

/**
 * Created by cout970 on 12/12/2015.
 */
public interface BlockMethod {

    interface OnHarvested extends BlockMethod {
        void onHarvested(WorldRef ref, IBlockData data, IPlayer player);
    }

    interface OnPlacedBy extends BlockMethod {
        void onPlacedBy(WorldRef ref, IBlockData data, ILivingEntity placer, IItemStack stack);
    }

    interface OnRemovedByPlayer extends BlockMethod {
        boolean onRemovedByPlayer(WorldRef ref, IPlayer player, boolean willHarvest);
    }

    interface OnAdded extends BlockMethod {
        void onAdded(WorldRef ref, IBlockData fromBlockData);
    }

    interface OnBreaks extends BlockMethod {
        void onBreaks(WorldRef ref, IBlockData data);
    }

    interface OnNeighborChange extends BlockMethod {
        void onNeighborBlockChange(WorldRef ref, IBlockData data, IBlock neighbor);
    }

    interface OnActivated extends BlockMethod {
        /**
         * Called when the player right click the block
         *
         * @param ref    the block reference
         * @param data   the block data
         * @param player the player who click the block
         * @param side   the side of the block clicked
         * @param ray    the the point where the player hit the block
         * @return true if the player should not try to do more stuff before calling this method, like placing a block or use an item
         */
        boolean onActivated(WorldRef ref, IBlockData data, IPlayer player, Direction side, Vect3d ray);
    }

    interface OnEntityCollided extends BlockMethod {
        void onEntityCollided(WorldRef ref, IEntity entity);
    }

    interface OnPlaced extends BlockMethod {
        IBlockData onPlaced(WorldRef ref, Direction side, ILivingEntity entity, Vect3d hit, int metadata);
    }

    interface OnClicked extends BlockMethod {
        void onClicked(WorldRef ref, IPlayer player);
    }

    interface AllBlockMethods extends OnHarvested, OnPlacedBy,
            OnRemovedByPlayer, OnAdded, OnBreaks, OnNeighborChange,
            OnActivated, OnEntityCollided, OnPlaced, OnClicked {
    }
}
