#!/usr/bin/env bash

loremipsum() {
  if [ "${1}" = "" ] || [ "${2}" = "" ]; then
    echo "Usage: loremipsum [paragraphs, sentences] [integer]"
  else
    curl -s http://metaphorpsum.com/"${1}"/"${2}" && printf "\n"
  fi
}

mkdir -p input input/binaries input/binaries/random input/text

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

curl "https://uploadbeta.com/api/pi/?cached&n=100000" -o ./input/pi.txt

dd if=/dev/random of=input/binaries/random/1 bs=4096 count=4096
dd if=/dev/random of=input/binaries/random/2 bs=4096 count=4096
dd if=/dev/random of=input/binaries/random/3 bs=4096 count=4096
dd if=/dev/random of=input/binaries/random/4 bs=4096 count=4096
dd if=/dev/random of=input/binaries/random/5 bs=4096 count=4096
dd if=/dev/zero of=input/binaries/zeros.bin bs=4096 count=4096

