#!/usr/bin/env python3
# -*-encoding: utf-8 -*-

import errno
import os
import shutil
import stat
import subprocess
import sys


def mv(source, target):
    try:
        shutil.move(source, target)
    except:
        pass


def cp(source, target):
    try:
        shutil.copyfile(source, target)
    except:
        pass


def cp_r(source, target):
    try:
        shutil.copytree(source, target)
    except:
        raise


def rm(file):
    try:
        os.remove(file)
    except OSError as e:
        if e.errno != errno.ENOENT:
            raise


def rm_on_error(func, path, _):
    # Remove a read-only file on Windows will get "WindowsError: [Error 5] Access is denied"
    # Clear the "read-only" and retry
    os.chmod(path, stat.S_IWRITE)
    os.unlink(path)


def rm_rf(path):
    if os.path.isdir(path) or os.path.islink(path):
        shutil.rmtree(path, ignore_errors=True, onerror=rm_on_error)
    else:
        rm(path)


def mkdir(path, mode=0o755):
    try:
        os.mkdir(path, mode)
    except:
        pass


def mkdir_p(path, mode=0o755):
    os.makedirs(path, mode, exist_ok=True)


def system(cmd):
    return subprocess.run(cmd, shell=True, stdout=sys.stdout, stderr=sys.stderr)


def buildTask(task):
    return system(f"{gradlew} {task}")


if __name__ == '__main__':
    LOCALDIR = os.path.dirname(__file__)
    gradlew = os.path.join(LOCALDIR, "gradlew")
    buildDir = os.path.join(LOCALDIR, "PermissionManager", "build")
    jar = os.path.join(buildDir, "libs", "PermissionManager.jar")
    jarName = os.path.basename(jar)
    outDir = os.path.join(LOCALDIR, "out")

    rm_rf(outDir)
    rm_rf(buildDir)
    mkdir_p(outDir)

    buildTask("makeJar")

    if os.path.exists(jar):
        mkdir_p(outDir)
        cp(jar, os.path.join(outDir, jarName))
        print("Output: " + outDir + os.sep + "PermissionManager.jar")
