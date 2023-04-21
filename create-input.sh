#!/usr/bin/env bash

loremipsum() {
  if [ "${1}" = "" ] || [ "${2}" = "" ]; then
    echo "Usage: loremipsum [paragraphs, sentences] [integer]"
  else
    curl -s http://metaphorpsum.com/"${1}"/"${2}" && printf "\n"
  fi
}

mkdir -p input input/binaries input/binaries/random

if [ ! -d "input/mini" ]; then
  mkdir -p input/mini
  for run in {1..10000}; do
    cp lorem.txt input/mini/$run.txt
  done
fi
if [ ! -d "input/text" ]; then
  mkdir -p input/text
  loremipsum paragraphs 50 > input/text/article.txt
  loremipsum paragraphs 50 > input/text/book.txt
  loremipsum paragraphs 50 >> input/text/book.txt
  loremipsum paragraphs 50 >> input/text/book.txt
  loremipsum paragraphs 50 >> input/text/book.txt
  loremipsum paragraphs 50 > input/text/epos.txt
  loremipsum paragraphs 50 >> input/text/epos.txt
  loremipsum paragraphs 50 >> input/text/epos.txt
  loremipsum paragraphs 50 >> input/text/epos.txt
  loremipsum paragraphs 50 >> input/text/epos.txt
  loremipsum paragraphs 50 >> input/text/epos.txt
  loremipsum paragraphs 50 >> input/text/epos.txt
  loremipsum paragraphs 50 >> input/text/epos.txt
  loremipsum paragraphs 50 >> input/text/epos.txt
fi
if [ ! -f "input/pi.txt" ]; then
  curl "https://uploadbeta.com/api/pi/?cached&n=100000" -o ./input/pi.txt
fi
for i in {1..5}; do
  if [ -f input/binaries/random/$i ]; then
    dd if=/dev/random of=input/binaries/random/$i bs=4096 count=4096
  fi
done
if [ -f input/binaries/zeros.bin ]; then
  dd if=/dev/zero of=input/binaries/zeros.bin bs=4096 count=4096
fi


