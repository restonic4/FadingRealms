package me.restonic4.fading_realms.data;

import me.restonic4.restapi.RestApi;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.Objects;
import java.util.UUID;

import static me.restonic4.fading_realms.FadingRealms.MOD_ID;

public class ModSavedData extends SavedData {
    private ListTag players = new ListTag();

    public static ModSavedData create() {
        return new ModSavedData();
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag tagList = new ListTag();

        for (int i = 0; i < players.size(); i++) {
            tagList.add(i, players.get(i));
        }

        tag.put("players", tagList);

        return tag;
    }

    public static ModSavedData load(CompoundTag tag) {
        ModSavedData data = create();

        data.players = tag.getList("players", Tag.TAG_COMPOUND);

        RestApi.Log("Players saved: " + data.players.size());

        return data;
    }

    public static ModSavedData manage(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(ModSavedData::load, ModSavedData::create, MOD_ID + "_players");
    }

    public void setPosition(UUID uuid, int x, int y, int z) {
        boolean found = false;

        //Found player
        for (Tag tag : players) {
            CompoundTag compound = (CompoundTag) tag;
            UUID foundUUID = compound.getUUID("uuid");

            if (Objects.equals(foundUUID.toString(), uuid.toString())) {
                compound.putInt("x", x);
                compound.putInt("y", y);
                compound.putInt("z", z);

                found = true;
            }
        }

        //Not found, creating data for player
        if (!found) {
            CompoundTag newTag = new CompoundTag();

            newTag.putUUID("uuid", uuid);

            newTag.putInt("x", x);
            newTag.putInt("y", y);
            newTag.putInt("z", z);

            players.add(players.size(), newTag);
        }

        //Forge's update thingy, idk, it's weird
        this.setDirty();
    }

    public boolean isEmpty(UUID uuid) {
        boolean isEmpty = true;

        for (Tag tag : players) {
            CompoundTag compound = (CompoundTag) tag;
            UUID foundUUID = compound.getUUID("uuid");

            RestApi.Log("Player uuid: " + uuid + ", found: " + foundUUID, MOD_ID);

            if (Objects.equals(foundUUID.toString(), uuid.toString())) {
                isEmpty = false;
            }
        }

        return isEmpty;
    }

    public BlockPos getPosition(UUID uuid) {
        return new BlockPos(getX(uuid), getY(uuid), getZ(uuid));
    }

    public int getX(UUID uuid) {
        int cord = 0;

        for (Tag tag : players) {
            CompoundTag compound = (CompoundTag) tag;
            UUID foundUUID = compound.getUUID("uuid");

            if (foundUUID == uuid) {
                cord = compound.getInt("x");
            }
        }

        return cord;
    }

    public int getY(UUID uuid) {
        int cord = 0;

        for (Tag tag : players) {
            CompoundTag compound = (CompoundTag) tag;
            UUID foundUUID = compound.getUUID("uuid");

            if (foundUUID == uuid) {
                cord = compound.getInt("y");
            }
        }

        return cord;
    }

    public int getZ(UUID uuid) {
        int cord = 0;

        for (Tag tag : players) {
            CompoundTag compound = (CompoundTag) tag;
            UUID foundUUID = compound.getUUID("uuid");

            if (foundUUID == uuid) {
                cord = compound.getInt("z");
            }
        }

        return cord;
    }

    /*public Vec3 getPosition() {
        return new Vec3(x_pos, y_pos, z_pos);
    }

    public int getX() {
        return x_pos;
    }

    public int getY() {
        return y_pos;
    }

    public int getZ() {
        return z_pos;
    }

    public boolean isEmpty() {
        return !isSaved;
    }*/
}